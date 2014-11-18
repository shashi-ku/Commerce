/**************************************************************
 * Classname: PrePrepaidOrderItemAddCmdImpl
 * Description:This command constructs the orderitem.field4 parameter
 * and also determines if the catentryId can be added to cart
 * Copyright Notice : Copyright (C) 2013 Airtel, All Rights Reserved
 * @author shashi
 **************************************************************/
package com.ibm.sf.commerce.orderitems.commands;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.ibm.commerce.catalog.facade.server.helpers.CatalogComponentHelper;
import com.ibm.commerce.command.TaskCommandImpl;
import com.ibm.commerce.datatype.TypedProperty;
import com.ibm.commerce.exception.ECApplicationException;
import com.ibm.commerce.exception.ECException;
import com.ibm.commerce.exception.ECSystemException;
import com.ibm.commerce.ras.ECMessage;
import com.ibm.commerce.ras.ECMessageHelper;
import com.ibm.sf.commerce.common.constants.SFConstants;
import com.ibm.sf.commerce.common.helpers.SFHelper;
import com.ibm.sf.commerce.ras.SFMessage;

public class PrePrepaidOrderItemAddTaskCmdImpl extends TaskCommandImpl implements PrePrepaidOrderItemAddTaskCmd {

	private static final String CLASSNAME = PrePrepaidOrderItemAddTaskCmdImpl.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);


	private Hashtable<Integer, String> hshOrderitemField2 = null;

	private Hashtable<Integer, String> ihshPrepaidCatentry_id = null;
	private Hashtable<Integer, String> ihshPrepaidCatentryField4 = null;
	
	/**
	 * This method is validates whether there is catalog Id 
	 * in request so that it can be used to construct 
	 * orderitem.field2
	 */
	@Override
	public void validateParameters() throws ECException {
		final String methodName = "validateParameters";
		LOGGER.entering(CLASSNAME, methodName);
		super.validateParameters();
		boolean isMasterCatalog = false;
		TypedProperty hshNVPs = new TypedProperty();
		String catalogId = getCommandContext().getRequestProperties().getString(SFConstants.EC_CATALOG_ID, null);
		if (StringUtils.isEmpty(catalogId)) {
			hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_CATALOG_ID_MISSING);
			throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName, ECMessageHelper
					.generateMsgParms(SFConstants.EC_CATALOG_ID), hshNVPs);
		}
		try {
			isMasterCatalog = CatalogComponentHelper.isMasterCatalog(catalogId, getCommandContext().getStoreId()
					.toString());

		} catch (Exception e) {
			throw new ECSystemException(ECMessage._ERR_GENERIC, CLASSNAME, methodName, ECMessageHelper
					.generateMsgParms("An Error Occurred: " + e.getMessage(), e));
		}
		if (isMasterCatalog) {
			hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_CATALOG_ID_MISSING);
			throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName, ECMessageHelper
					.generateMsgParms(SFConstants.EC_CATALOG_ID), hshNVPs);

		}
			LOGGER.exiting(CLASSNAME, methodName);
	}

	/**
	 * This method is responsible for constructing orderitem.field4 by appending
	 * catalogId_productType_categoryAttribute.It also detrmines whether the
	 * catentryId can be added to the current shopping cart.
	 * 
	 */
	@Override
	public void performExecute() throws ECException {
		final String methodName = "performExecute";
		LOGGER.entering(CLASSNAME, methodName);

		super.performExecute();

		if (null != ihshPrepaidCatentry_id && null!=ihshPrepaidCatentryField4 && ihshPrepaidCatentry_id.size()==ihshPrepaidCatentryField4.size()) {
			String catalogId = getCommandContext().getRequestProperties().getString(SFConstants.EC_CATALOG_ID, null);
			String attrDataTypeVal = null;
			StringBuilder  stringBuilder = new StringBuilder();
			for(Map.Entry<Integer, String> entry : ihshPrepaidCatentry_id.entrySet()){
				stringBuilder.setLength(0);
				stringBuilder.append(catalogId).append(SFConstants.STRING_HYPHEN).append(ihshPrepaidCatentryField4.get(entry.getKey()));
				attrDataTypeVal = SFHelper.getInstance().getAttributeValue(entry.getValue(),
						SFConstants.ATTR_PPP_CATEGORY_IDENTIFIER);
				if (StringUtils.isNotEmpty(attrDataTypeVal)) {
					stringBuilder.append(SFConstants.STRING_HYPHEN).append(attrDataTypeVal);
				}
				
				if(null==hshOrderitemField2){
					hshOrderitemField2 = new Hashtable<Integer, String>();
				}
				hshOrderitemField2.put(entry.getKey(), stringBuilder.toString());
			}
			
		}
		LOGGER.exiting(CLASSNAME, methodName);

	}


	/**
	 * 
	 */
	@Override
	public Hashtable<Integer, String> getHshOrderItemField2() {
		final String methodName = "getHshOrderItemField2";
		LOGGER.entering(CLASSNAME, methodName);
		LOGGER.exiting(CLASSNAME, methodName);
		return hshOrderitemField2;

	}


	/**
	 * Setting the value of ihshPrepaidCatentry_id
	 * @param the ihshPrepaidCatentry_id - Hashtable<Integer,String>
	 */
	public void setIhshPrepaidCatentry_id(Hashtable<Integer, String> ihshPrepaidCatentry_id) {
		this.ihshPrepaidCatentry_id = ihshPrepaidCatentry_id;
	}

	/**
	 * Setting the value of ihshPrepaidCatentryField4
	 * @param the ihshPrepaidCatentryField4 - Hashtable<Integer,String>
	 */
	public void setIhshPrepaidCatentryField4(Hashtable<Integer, String> ihshPrepaidCatentryField4) {
		this.ihshPrepaidCatentryField4 = ihshPrepaidCatentryField4;
	}

}
