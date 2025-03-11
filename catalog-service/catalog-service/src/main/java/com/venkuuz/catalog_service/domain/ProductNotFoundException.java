package com.venkuuz.catalog_service.domain;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String message) {
    super(message);
  }

  public static ProductNotFoundException forCode(String code) {
    return new ProductNotFoundException("The product with code :" + code + " not found");
  }
}
