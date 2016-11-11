package com.sasd13.proadmin.wrapper.read;

import java.util.List;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public interface IReadWrapper<T> {

    List<T> getWrapped();
}
