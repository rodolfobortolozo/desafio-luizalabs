package br.com.luizalabs.application.usecases;

import br.com.luizalabs.adapter.persistence.models.*;
import br.com.luizalabs.domain.File;
import br.com.luizalabs.infrastructure.exceptions.FileException;

public interface IProcessFile {

    void process();

    ClientData processClient(FileData file);

    OrderData processOrder(FileData file, ClientData client);

    ProductData processProduct(FileData file);

    ItemData processItem(FileData file, OrderData orderData, ProductData productData);

    default String getSubstringInRange(String str, int start, int end) {
        if (start < 0 || start >= str.length() || end <= start || end > str.length()) {
            throw new FileException("Índices de range inválidos.");
        }
        return str.substring(start, end);
    }

}
