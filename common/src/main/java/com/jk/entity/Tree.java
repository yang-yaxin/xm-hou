package com.jk.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tree {
    private Integer id;
    private String text;
    private String href;
    private String pid;
    private List<Tree> nodes;
    private Boolean selectable;
    private Boolean leaf;
}
