package com.poc.utils;

import java.util.List;

public class HelpPage<T> {

    private static final int RESULTS_PER_PAGE = 5;

    private int pageNumber;
    private int resultsPerPage;
    private int totalResults;
    private List<T> items;

    public HelpPage(int pageNumber, int totalResults, List<T> items) {
        this.pageNumber = pageNumber;
        this.resultsPerPage = RESULTS_PER_PAGE;
        this.totalResults = totalResults;
        this.items = items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
