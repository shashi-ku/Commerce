/**************************************************************
 * Classname:SFOrderItemAddCmdImpl
 * Description:This command extends the OOB OrderItemAddCmdImpl
 * to implement custom business logic during add to cart
 * Copyright Notice : Copyright (C) 2013 Airtel, All Rights Reserved
 * @author shashi
 **************************************************************/
package com.ibm.sf.commerce.orderitems.commands;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import com.ibm.commerce.catalog.facade.server.helpers.CatalogComponentHelper;
import com.ibm.commerce.command.CommandFactory;
import com.ibm.commerce.datatype.TypedProperty;
import com.ibm.commerce.exception.ECApplicationException;
import com.ibm.commerce.exception.ECException;
import com.ibm.commerce.exception.ECSystemException;
import com.ibm.commerce.order.objects.OrderAccessBean;
import com.ibm.commerce.order.utils.OrderConstants;
import com.ibm.commerce.orderitems.commands.OrderItemAddCmdImpl;
import com.ibm.commerce.ras.ECMessage;
import com.ibm.commerce.ras.ECMessageHelper;
import com.ibm.commerce.ras.ECMessageKey;
import com.ibm.commerce.requisitionlist.commands.RequisitionListCreateCmd;
import com.ibm.commerce.requisitionlist.commands.RequisitionListItemUpdateCmd;
import com.ibm.sf.commerce.common.constants.SFConstants;
import com.ibm.sf.commerce.ras.SFMessage;

public class SFOrderItemAddCmdImpl extends OrderItemAddCmdImpl {

	private static final String CLASSNAME = SFOrderItemAddCmdImpl.class.getName();

	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	private TypedProperty requisitionListCreateInputProp = null;

	private TypedProperty requisitionListUpdateInputProp = null;

	private String[] arrOrderItemIds = null;

	private String reqListParentOrderId = null;

	@Override
	public void setRequestProperties(TypedProperty requestProperties) throws ECException {
		final String METHODNAME = "setRequestProperties";
		
		reqListParentOrderId = requestProperties.getString(SFConstants.REQ_LIST_PARENT_ORDERID, null);
		super.setRequestProperties(requestProperties);
		boolean isRequisitionListFlow = requestProperties.getBoolean(SFConstants.IS_REQUISITIONLISTFLOW, false);
		getRequestProperties().put(SFConstants.IS_REQUISITIONLISTFLOW, isRequisitionListFlow);

	}

	/**
	 * This method invokes is responsible for adding error codes to the custom
	 * and OOB exception messages.
	 * 
	 * @throws ECException
	 */
	@Override
	public void validateParameters() throws ECException {
		final String methodName = "validateParameters";
		TypedProperty hshNVPs = new TypedProperty();
		LOGGER.entering(CLASSNAME, methodName);
		boolean isMasterCatalog = false;
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

		if (null == ihshCatentry_id || ihshCatentry_id.isEmpty()) {

			hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_CATENTRY_ID_MISSING);
			throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName, ECMessageHelper
					.generateMsgParms(SFConstants.EC_PRODUCT_ID), hshNVPs);

		} else {
			for (Object cateEntryId : ihshCatentry_id.values()) {
				if (StringUtils.isEmpty((String) cateEntryId)) {
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_CATENTRY_ID_MISSING);
					throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_PRODUCT_ID), hshNVPs);

				}
			}
		}

		if (null == ihshQuantity || ihshQuantity.isEmpty()) {

			hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_QUANTITY_MISSING);
			throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName, ECMessageHelper
					.generateMsgParms(SFConstants.EC_QUANTITY), hshNVPs);

		} else {
			for (Object quantity : ihshQuantity.values()) {
				if (StringUtils.isEmpty((String) quantity)) {
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_QUANTITY_MISSING);
					throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_QUANTITY), hshNVPs);

				}
			}
		}

		try {
			super.validateParameters();
		} catch (ECApplicationException e) {
			// add error code to any OOB application exception
			TypedProperty exceptionFields = e.getExceptionFields();
			if (null != exceptionFields && !exceptionFields.isEmpty()) {
				if (exceptionFields.containsKey(SFConstants.EC_CATALOG_ENTRY_ID)
						&& (ECMessageKey._ERR_INVALID_INPUT).equalsIgnoreCase(e.getMessageKey())) {
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_CATENTRY_ID_INVALID);
					throw new ECApplicationException(SFMessage.SF_ERR_INVALID_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_CATALOG_ENTRY_ID), hshNVPs);
				}
				if (exceptionFields.containsKey(SFConstants.EC_QUANTITY)
						&& (ECMessageKey._ERR_INVALID_INPUT).equalsIgnoreCase(e.getMessageKey())) {
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_QUANTITY_INVALID);
					throw new ECApplicationException(SFMessage.SF_ERR_INVALID_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_QUANTITY), hshNVPs);
				}

				throw e;

			}
			throw e;
		}
		LOGGER.exiting(CLASSNAME, methodName);
	}

	/**
	 * This method invokes PreOrderItemAddCmd for pre processing logic before
	 * calling super.performExecute .It also invokes PostOrderItemAddCmd for
	 * post processing logic after calling super.performexecute.
	 * 
	 * @throws ECException
	 */
	@Override
	public void performExecute() throws ECException {
		final String methodName = "performExecute";
		LOGGER.entering(CLASSNAME, methodName);
		PreOrderItemAddCmd preOrderItemAddCmd = (com.ibm.sf.commerce.orderitems.commands.PreOrderItemAddCmd) CommandFactory
				.createCommand("com.ibm.sf.commerce.orderitems.commands.PreOrderItemAddCmd", getCommandContext()
						.getStoreId());
		preOrderItemAddCmd.setRequestProperties(getRequestProperties());
		preOrderItemAddCmd.setHshCatentryIds(ihshCatentry_id);
		preOrderItemAddCmd.setHshQuantity(ihshQuantity);
		preOrderItemAddCmd.setCommandContext(getCommandContext());
		preOrderItemAddCmd.execute();
		requisitionListCreateInputProp = preOrderItemAddCmd.getRequisitionListCreateInputProp();
		requisitionListUpdateInputProp = preOrderItemAddCmd.getRequisitionListUpdateInputProp();

		boolean isRequisitionListFlow = requestProperties.getBoolean("isRequisitionListFlow", false);

		if (isRequisitionListFlow) {

			createUpdateRequisitionList();

		} else {

			// fetch field2 populated from PreOrderItemAddCmd
			if (null != preOrderItemAddCmd.getHshOrderitemField2()
					&& !preOrderItemAddCmd.getHshOrderitemField2().isEmpty()) {
				ihshField2 = preOrderItemAddCmd.getHshOrderitemField2();
			}
			try {
				super.performExecute();
				String orderId = responseProperties.getString(OrderConstants.EC_ORDER_RN);
				getRequestProperties().put(OrderConstants.EC_ORDER_RN, orderId);
				arrOrderItemIds = getOrderItemIds();
			} catch (ECApplicationException e) {
				// add error code to any OOB application exception
				TypedProperty exceptionFields = e.getExceptionFields();
				if (null != exceptionFields && exceptionFields.containsKey(SFConstants.EC_QUANTITY)
						&& (ECMessageKey._ERR_INVALID_INPUT).equalsIgnoreCase(e.getMessageKey())) {
					TypedProperty hshNVPs = new TypedProperty();
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_QUANTITY_INVALID);
					throw new ECApplicationException(SFMessage.SF_ERR_INVALID_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_QUANTITY), hshNVPs);
				} else {
					throw e;
				}

			}

		}

		if (null != arrOrderItemIds && arrOrderItemIds.length > 0) {
			PostOrderItemAddCmd postOrderItemAddCmd = (PostOrderItemAddCmd) CommandFactory.createCommand(
					"com.ibm.sf.commerce.orderitems.commands.PostOrderItemAddCmd", getCommandContext().getStoreId());
			postOrderItemAddCmd.setRequestProperties(getRequestProperties());
			postOrderItemAddCmd.setOrderItemIds(arrOrderItemIds);
			postOrderItemAddCmd.setCommandContext(getCommandContext());
			postOrderItemAddCmd.execute();
		}

		LOGGER.exiting(CLASSNAME, methodName);

	}
	/**
	 * This method creates a requisition list or adds new items to existing
	 * requisition list.
	 * 
	 * @throws ECException
	 */
	private void createUpdateRequisitionList() throws ECException {
		final String methodName = "createUpdateRequisitionList";
		LOGGER.entering(CLASSNAME, methodName);
		if (null == responseProperties) {
			responseProperties = new TypedProperty();
		}

		Long requisitionListId = null;
		if (null != requisitionListCreateInputProp && requisitionListCreateInputProp.size() > 0) {

			RequisitionListCreateCmd requisitionListCreateCmd = (RequisitionListCreateCmd) CommandFactory
					.createCommand("com.ibm.commerce.requisitionlist.commands.RequisitionListCreateCmd",
							getCommandContext().getStoreId());
			requisitionListCreateCmd.setRequestProperties(requisitionListCreateInputProp);
			requisitionListCreateCmd.setCommandContext(getCommandContext());
			requisitionListCreateCmd.execute();
			requisitionListId = requisitionListCreateCmd.getRequisitionListId();
			// update duplicate requisition list field3 with  parent order id
			if (StringUtils.isNotEmpty(reqListParentOrderId)) {
				OrderAccessBean orderAccessBean = new OrderAccessBean();
				orderAccessBean.setInitKey_orderId(requisitionListId.toString());

				try {
					orderAccessBean.setField3(reqListParentOrderId);
					orderAccessBean.commitCopyHelper();
				} catch (RemoteException e) {
					throw new ECSystemException(ECMessage._ERR_REMOTE_EXCEPTION, CLASSNAME, methodName, e);
				} catch (NamingException e) {
					throw new ECSystemException(ECMessage._ERR_NAMING_EXCEPTION, CLASSNAME, methodName, e);
				} catch (FinderException e) {
					throw new ECSystemException(ECMessage._ERR_FINDER_EXCEPTION, CLASSNAME, methodName, e);
				} catch (CreateException e) {
					throw new ECSystemException(ECMessage._ERR_CREATE_EXCEPTION, CLASSNAME, methodName, e);
				}

			}
			responseProperties.put(SFConstants.EC_ORDER_RN, requisitionListCreateCmd.getRequisitionListId().toString());
			requestProperties.put(SFConstants.EC_ORDER_RN, requisitionListCreateCmd.getRequisitionListId().toString());

		}
		if (null != requisitionListUpdateInputProp && requisitionListUpdateInputProp.size() > 0) {

			if (null == requisitionListId) {
				requisitionListId = getRequestProperties().getLong(OrderConstants.EC_REQUISITION_LIST_ID, null);
			}
			if (null != requisitionListId) {
				requisitionListUpdateInputProp.put(OrderConstants.EC_REQUISITION_LIST_ID, requisitionListId.toString());
				responseProperties.put(SFConstants.EC_ORDER_RN, requisitionListId.toString());
			}
			RequisitionListItemUpdateCmd requisitionListItemUpdateCmd = (RequisitionListItemUpdateCmd) CommandFactory
					.createCommand("com.ibm.commerce.requisitionlist.commands.RequisitionListItemUpdateCmd",
							getCommandContext().getStoreId());
			requisitionListItemUpdateCmd.setRequestProperties(requisitionListUpdateInputProp);
			requisitionListItemUpdateCmd.setCommandContext(getCommandContext());
			requisitionListItemUpdateCmd.execute();
			arrOrderItemIds = requisitionListItemUpdateCmd.getOrderItemIds();
			if(null!=arrOrderItemIds){
				responseProperties.put(SFConstants.EC_ORDERITEM_RN, arrOrderItemIds);
				requestProperties.put(SFConstants.EC_ORDERITEM_RN, arrOrderItemIds);
			}
			
			requestProperties.put(SFConstants.EC_ORDER_RN, responseProperties.getString(SFConstants.EC_ORDER_RN));
		}

		LOGGER.exiting(CLASSNAME, methodName);

	}

}
