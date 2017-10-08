package com.acupt.dao.query;

/**
 * Created by liujie on 2017/10/7.
 */
public abstract class PageableQuery extends AbstractQuery {

    private int _page = 1;

    private int _size = 10;

    public Integer get_page() {
        return _page;
    }

    public void set_page(Integer _page) {
        this._page = _page;
    }

    public Integer get_size() {
        return _size;
    }

    public void set_size(Integer _size) {
        this._size = _size;
    }
}
