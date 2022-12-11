package com.example.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
  private String from;
  private String to;
  private String body;
}
