package br.com.luizalabs.application.services;

import br.com.luizalabs.adapter.persistence.models.*;
import br.com.luizalabs.adapter.persistence.repository.*;
import br.com.luizalabs.application.usecases.IProcessFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProcessFileService implements IProcessFile {

    Logger logger = LoggerFactory.getLogger(ProcessFileService.class);

    private final FileRepository fileRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public ProcessFileService(FileRepository fileRepository, ClientRepository clientRepository, OrderRepository orderRepository, ProductRepository productRepository, ItemRepository itemRepository) {
        this.fileRepository = fileRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void process() {
        List<FileData> fileData = fileRepository.getPendingContent();

        logger.info("Iniciado o Processamento....");

        if (fileData.isEmpty()) {
            logger.info("Nenhum arquivo para importar....");
            return;
        }

        fileData.forEach(f ->{
            logger.info("Iniciando a importação da linha {} do arquivo {}",f.getLineNumber(), f.getFileName());
            //Cliente
            ClientData processClient = processClient(f);
            //Pedido
            logger.info("Importando Pedido....");
            OrderData processOrder = processOrder(f, processClient);
            //Produto
            logger.info("Importando Produto....");
            ProductData processProduct = processProduct(f);
            //Item Pedido
            logger.info("Importando Item Pedido....");
            processItem(f,processOrder,processProduct);
        });

        logger.info("Finalizado o Processamento....");
    }

    @Override
    public ClientData processClient(FileData file) {

        Long idCliente = Long.valueOf(getSubstringInRange(file.getContent(),0,10));
        String nomeCliente = getSubstringInRange(file.getContent(),11,55).trim();

        logger.info("Iniciando a importação do cliente {} do arquivo {}",idCliente +"-"+ nomeCliente,file.getFileName());

        clientRepository.findById(idCliente).ifPresentOrElse(cliente -> {
        },() ->{
            logger.info("Cliente {} já cadastro na base de dados", idCliente + "-" + nomeCliente);
        });

        ClientData clientData1 = ClientData.builder()
                .id(idCliente)
                .name(nomeCliente)
                .build();
        clientRepository.save(clientData1);



        return clientData1;
    }

    @Override
    public OrderData processOrder(FileData file, ClientData client) {

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String rawData = getSubstringInRange(file.getContent(),87,95);
        Long idPedido = Long.valueOf(getSubstringInRange(file.getContent(),56,65));

        logger.info("Iniciando a importação do pedido {} do arquivo {}",idPedido, file.getFileName());

        orderRepository.findById(idPedido).ifPresentOrElse(cliente -> {
        },() ->{
            logger.info("Pedido {} já cadastro na base de dados", idPedido);
        });

        LocalDate data = LocalDate.parse(rawData, formatter);
        OrderData orderData = OrderData.builder()
                .id(idPedido)
                .client(client)
                .dateOrder(data)
                .build();
        return orderRepository.save(orderData);

    }

    @Override
    public ProductData processProduct(FileData file){

        Long idProduto = Long.valueOf(getSubstringInRange(file.getContent(),66,75));

        logger.info("Iniciando a importação do produto {} do arquivo {}",idProduto, file.getFileName());

        productRepository.findById(idProduto).ifPresentOrElse(cliente -> {
        },() ->{
            logger.info("Produto {} já cadastro na base de dados", idProduto);
        });

        ProductData productData = ProductData.builder()
                .id(idProduto)
                .build();
        return productRepository.save(productData);
    }

    @Override
    public ItemData processItem(FileData file, OrderData orderData, ProductData productData) {

        logger.info("Iniciando a importação item do pedido {}",orderData.getId());

        BigDecimal price = new BigDecimal(getSubstringInRange(file.getContent(),76,87).trim());
        ItemData itemData = ItemData.builder()
                .order(orderData)
                .product(productData)
                .price(price)
                .build();
        ItemData item = itemRepository.save(itemData);

        //Altera Status
        fileRepository.updateStatusContent(file.getId());

        return item;
    }
}
