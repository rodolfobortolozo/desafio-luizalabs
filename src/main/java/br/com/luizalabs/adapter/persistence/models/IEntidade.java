package br.com.luizalabs.adapter.persistence.models;

import java.io.Serializable;

public interface IEntidade<K extends Serializable> {

    K getId();
}
