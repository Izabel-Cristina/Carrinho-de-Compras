package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResource {

    public ResponseEntity<?> createdCodeReturn(ResponseDTO<?> input) {
        if (!input.getCode().equals(0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(input);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(input);
        }
    }

    public ResponseEntity<?> updatedCodeReturn(ResponseDTO<?> input) {
        if (!input.getCode().equals(0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(input);
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(input);
        }
    }

    public ResponseEntity<?> findCodeReturn(ResponseDTO<?> input) {
        if (!input.getCode().equals(0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(input);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(input);
        }
    }

    public ResponseEntity<?> deleteCodeReturn(ResponseDTO<?> input) {
        if (!input.getCode().equals(0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(input);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(input);
        }
    }

    public ResponseEntity<?> list (ResponseDTO<?> input) {
        if (!input.getCode().equals(0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(input);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(input);
        }
    }
}


