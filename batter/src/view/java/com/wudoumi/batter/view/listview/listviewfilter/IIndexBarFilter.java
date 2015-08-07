// @author Bhavya Mehta
package com.wudoumi.batter.view.listview.listviewfilter;

// Gives index bar view touched Y axis value, position of section and preview text value to list view 
public interface IIndexBarFilter {
	void filterList(float sideIndexY,String previewText);
}
