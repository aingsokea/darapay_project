package com.darapay.loanreferral.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdvanceModel {
    public AdvanceModel () {}
    public AdvanceModel(String table, List<String> fields, List<String> joinTables, List<FilterModel> filterModels, PaginationModel paginationModel) {
        this.table = table;
        this.fields = fields;
        this.joinTables = joinTables;
        this.filterModels = filterModels;
        this.paginationModel = paginationModel;
    }

    public AdvanceModel(String table, List<String> fields, List<String> joinTables, List<FilterModel> filterModels, PaginationModel paginationModel, String groupBy) {
        this.table = table;
        this.fields = fields;
        this.joinTables = joinTables;
        this.filterModels = filterModels;
        this.paginationModel = paginationModel;
        this.groupBy = groupBy;
    }

    private String table;
    private List<String> fields;
    private List<String> joinTables;
    private List<FilterModel> filterModels;
    private PaginationModel paginationModel;
    private String groupBy;
}
