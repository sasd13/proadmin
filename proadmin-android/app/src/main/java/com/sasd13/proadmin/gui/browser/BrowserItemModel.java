package com.sasd13.proadmin.gui.browser;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.widget.IColorable;
import com.sasd13.androidex.gui.widget.IIconifiable;
import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;

import java.util.Observable;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserItemModel extends Observable implements IRecyclerItemModel, ILabelizable, IIconifiable, IColorable {

    private EnumBrowserItemType browserItemType;
    private String label;
    private Drawable icon;
    private int color;
    private Class<? extends AppCompatActivity> target;

    public BrowserItemModel(EnumBrowserItemType browserItemType, String label, Drawable icon, int color, Class<? extends AppCompatActivity> target) {
        this.browserItemType = browserItemType;
        this.label = label;
        this.icon = icon;
        this.color = color;
        this.target = target;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return browserItemType.getRecyclerItemType();
    }

    public EnumBrowserItemType getBrowserItemType() {
        return browserItemType;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Drawable getIcon() {
        return icon;
    }

    @Override
    public int getColor() {
        return color;
    }

    public Class<? extends AppCompatActivity> getTarget() {
        return target;
    }
}
