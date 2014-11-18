<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../Common/EnvironmentSetup.jspf" %>
<%@include file="../Common/nocache.jspf" %>
<%@include file="../Common/ReviewsSetup.jspf" %>
<%@ taglib uri="http://commerce.ibm.com/coremetrics"  prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://commerce.ibm.com/base" prefix="wcbase" %>
<%@ taglib uri="http://commerce.ibm.com/foundation" prefix="wcf"%>
<%@ taglib uri="flow.tld" prefix="flow" %>


<wcf:url var="GetPostpaidBoosters" value="GetPostpaidBoosters">
		<wcf:param name="langId" value="${langId}" />
		<wcf:param name="storeId" value="${storeId}" />
		<wcf:param name="catalogId" value="${catalogId}" />
		<wcf:param name="catalogIdentifier" value="${catalogIdentifier}" />
</wcf:url>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" 
      type="image/png" 
      href="http://localhost/webapp/wcs/stores/AuroraStorefrontAssetStore/images/images/airtel-logo.png">
<title>iplan</title>
<link href="/wcsstore/AuroraStorefrontAssetStore/css/iplan.css" rel="stylesheet" type="text/css" />
<link href="/wcsstore/AuroraStorefrontAssetStore/css/script.css" rel="stylesheet" type="text/css" />
<link href="/wcsstore/AuroraStorefrontAssetStore/css/font/font.css" rel="stylesheet" type="text/css" />
<link href="/wcsstore/AuroraStorefrontAssetStore/css/checbox.css" type="text/css" rel="stylesheet" />
<link href="/wcsstore/AuroraStorefrontAssetStore/css/nanoscroller.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/overthrow.min.js"></script>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery.min.js"></script>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/main.js"></script>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery.cycle.all.js"></script>
<%--
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/ga.js"></script>
--%>  
<script type="text/javascript">
function choosePostpaidNumber(nextButtonURL) {
$("#WC_PostpaidCartSummary_Next_a").attr("href", nextButtonURL);
}
function choosePostpaidNumber(itemId, number, nextButtonURL) {

$("#WC_PostpaidCartSummary_Number_ul").empty();
		
var newHTML;
newHTML = '<li id="WC_PostpaidCartSummary_Number_li_' + itemId +'">';
newHTML += number
newHTML += '</li>';
$("#WC_PostpaidCartSummary_Number_ul").append(newHTML);

$("#WC_PostpaidCartSummary_Next_a").attr("href", nextButtonURL);

$('#WC_PostpaidNumberSelection_ChooseYourNumber_div .active').removeClass('active');
$('#WC_PostpaidNumberSelection_div_'+number).addClass('active');

}

</script>  

<script>
$(document).ready(function() {
  $('#freeBieAdd a').click(function() {
     var url=$(this).attr('href');
     $.get(url,processData);
     function processData(data,status){
     console.log( 'status = ' + status);
     }
     return false;
  }); //end click
			
}); // end ready
</script>
<!--[if IE 7]>	
<link rel="stylesheet" type="text/css" media="all" href="css/ie7.css" />
<![endif]-->

</head>
<body class="has-js">

<div id="template">
<div class="main">

<!-- start .dummy_header -->
<img src="/wcsstore/AuroraStorefrontAssetStore/images/colors/color1/transparent.gif" alt="No image available"/>
<div class="dummy_header"><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/header_bg.jpg" /></div>
<!-- end .dummy_header -->
 
<!-- start .main_inner -->
<div class="main_inner">
<div class="top_core"><div class="close1"><a href="#"><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/close.jpg" /></a></div></div> 
<div class="clear"></div>
<div class="middle_bg">
 
<!-- start .container -->
<div class="middle_content">
<div class="middle_content_top">
<div class="middle_content_top_left">
<h1><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/heading.jpg" /></h1>
Airtel has launched iPlan for the first time ever, in India's telecom history. Now you can create your own plan as per your usage by following some simple steps. Just follow the on screen instruction to create your own plan. For any further help you can choose to watch the video guide provided on the right.
</div> 
</div>
<div class="clear"></div>
 <!-- 918px --> 
 <div class="topatab4">
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active" id="select_rent">select a <span>monthly rent</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">claim your <span>freebies</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">add <span>boosters</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">select <span>number</span></td>
       </tr>
     </table>
   </div>
   
   
  	<div class="clear"></div>
	<div class="popup-inner">
	
 	<!-- Left -->
 	<div class="popup-inner-left">
 	
	
	<h1>choose your number <span><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/right-corner.jpg" alt="choose you boosters" border="0"></span></h1>
    	<div class="popup-inner-left-inner">
        <!-- start .left_pannel -->
			<div id="about" class="nano">
				<div class="content"> 
					<div id="WC_PostpaidNumberSelection_ChooseYourNumber_div" class="frebies_left">
                    <!-- Tiles -->
                    
                    <!-- Iterate Numbers Here  -->
                    
					<c:forEach items="${postpaidNumbers}" var="onlineResponseBO" varStatus="itemStatus"> 
					 
	
						<c:if test="${((itemStatus.index + 1) mod 2) == 1}">
							<c:set var="divClass" value="choose-your-plan-left"/>
						</c:if>
						<c:if test="${((itemStatus.index + 1) mod 2) == 0}">
							<c:set var="divClass" value="choose-your-plan-right"/>
						</c:if>
						
						<wcf:url var="PostpaidOrderItemAddURL"
								value="PostpaidOrderItemAdd">
								<wcf:param name="langId" value="${langId}" />
								<wcf:param name="storeId" value="${storeId}" />
								<wcf:param name="catalogId" value="${catalogId}" />
								<wcf:param name="catEntryId" value="${postPaidNumberItemId}" />
								<wcf:param name="quantity" value="1"/>
								<wcf:param name="field2" value="${field2}"/>
								<wcf:param name="postpaidNumber" value="${onlineResponseBO.externalId}"/>
								<wcf:param name="vanityCode" value="${onlineResponseBO.vanityCode}"/>
								<wcf:param name="vanityNumberPrice" value="${onlineResponseBO.price}"/>
								<wcf:param name="catalogIdentifier" value="${catalogIdentifier}" />
						</wcf:url>

						<a href='javascript:choosePostpaidNumber("19501","${onlineResponseBO.externalId}","${PostpaidOrderItemAddURL}");'>
						<div id="WC_PostpaidNumberSelection_div_${onlineResponseBO.externalId}" class="${divClass}">
							<div class="choose-your-plan-cont">${onlineResponseBO.externalId}</div>
							<div class="choose-your-plan-bottom-cont">&nbsp;</div>
							<%-- 
							<div class="choose-your-plan-bottom-cont">@ Rs. ${onlineResponseBO.price} </div>
							--%>
							<%--
							<img src="/wcsstore/AuroraStorefrontAssetStore/images/images/phone-icon.jpg" alt="mobile">							
						   	<span>&nbsp;&nbsp; ${item} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						   	--%>
						</div>
						</a>
					</c:forEach>
                    
                    <!-- End Iteration -->
                    
                    <!-- Tiles end-->
                    </div>
				</div> 
				<div class="pane"><div style="height: 344px; top: 0px;" class="slider"></div></div>
			</div>
			<!-- end .left_pannel -->
            <div class="clear"></div>
        </div> 
    <div><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/popup_bottom.jpg" alt="boosters" border="0"></div>   
	 	
 	
 	
 	
 	
 	 
	</div>
	<!-- Left -->
	<!-- Right -->
	
    <%out.flush();%>
	<c:import url="PostpaidCartSummary.jsp">
		<c:param name="pageIdentifier" value="Number"/>
		<c:param name="previousURL" value="${GetPostpaidBoosters}"></c:param>
		<c:param name="nextURL" value="#"></c:param>
	</c:import>
	<%out.flush();%>
	<!-- Right -->

	
    <div class="clear"></div>
    
		

    
 	</div>
 <!-- 918px end -->
 <div class="clear"></div>
 </div>
  <!-- end .container -->
 </div> 
 <div class="clear"></div>
 <div class="bottom_core"></div>
 </div>
  <!-- end .main_inner -->


</div>
</div>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/custom.js"></script>
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery.dd.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function() {

	try {
			$("#websites3").msDropDown();
			$("#websites2").msDropDown({mainCSS:'dd2'});
			$("#websites4").msDropDown({mainCSS:'dd2'});
			$("#websites5").msDropDown({mainCSS:'dd2'});
			$("#websites6").msDropDown({mainCSS:'dd2'});
			$("#websites7").msDropDown({mainCSS:'dd2'});
			$("#websites8").msDropDown({mainCSS:'dd2',openDirection: 'alwaysUp'});
			$("#websites9").msDropDown({mainCSS:'dd2',openDirection: 'alwaysUp'});
			//alert($.msDropDown.version);
			$.msDropDown.create("body select");
			$("#ver").html($.msDropDown.version);
			} catch(e) {
				//alert("Error: "+e.message);
			}
	})
</script>

</body>
</html>
