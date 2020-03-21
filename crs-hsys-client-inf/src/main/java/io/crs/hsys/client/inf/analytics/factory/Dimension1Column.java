/**
 * 
 */
package io.crs.hsys.client.inf.analytics.factory;

import gwt.material.design.client.ui.table.cell.TextColumn;
import io.crs.hsys.client.inf.analytics.model.ModelD1M1;

/**
 * @author CR
 *
 */
public class Dimension1Column extends TextColumn<ModelD1M1> {

	@Override
	public String getValue(ModelD1M1 object) {
		return object.getDimension1();
	}

}
