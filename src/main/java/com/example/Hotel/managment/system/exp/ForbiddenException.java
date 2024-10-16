package com.example.Hotel.managment.system.exp;

public class ForbiddenException extends RuntimeException{
        public ForbiddenException(String massage){
            super(massage);
        }
}
