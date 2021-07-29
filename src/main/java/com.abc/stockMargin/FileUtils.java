package com.zuhlke.stockMargin;

import java.util.List;

public interface FileUtils {

    public List<Stocks> parse(String filePath);
}
