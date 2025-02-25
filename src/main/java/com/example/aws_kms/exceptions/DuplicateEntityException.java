package com.example.aws_kms.exceptions;



public class DuplicateEntityException  extends RuntimeException{
        public DuplicateEntityException(String entity,String field) {
            super("Duplicate "+field+"! "+entity+" already exists.");


        }
}
