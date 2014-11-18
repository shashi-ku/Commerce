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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
<script type="text/javascript" src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/ga.js"></script>
  
<script src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery-1.9.1.js"></script>
<script src="/wcsstore/AuroraStorefrontAssetStore/javascript/js/jquery-ui.js"></script>

<link rel="stylesheet" href="/wcsstore/AuroraStorefrontAssetStore/css/jquery-ui.css" />
	
	<script>
	$(function() {
		$( "#slider-range-min" ).slider({
			range: "min",
			value: 0,
			min: 0,
			max: 9999,
			slide: function( event, ui ) {
				$( "#amount" ).val( ui.value + " minutes");
			}
		});	
		$( "#amount" ).val($( "#slider-range-min" ).slider( "value" ) + " minutes" );

		$( "#slider-range-min1" ).slider({
			range: "min",
			value: 0,
			min: 0,
			max: 10,
			slide: function( event, ui ) {
				$( "#amount1" ).val( ui.value + " GB");
			}
		});	
		$( "#amount1" ).val($( "#slider-range-min1" ).slider( "value" ) + " GB" );
	
		$( "#slider-range-min2" ).slider({
			range: "min",
			value: 0,
			min: 0,
			max: 100,
			slide: function( event, ui ) {
				$( "#amount2" ).val( ui.value + " SMS");
			}
		});	
		$( "#amount2" ).val($( "#slider-range-min2" ).slider( "value" ) + " SMS" );

		$( "#slider-range-min3" ).slider({
			range: "min",
			value: 0,
			min: 0,
			max: 100,
			slide: function( event, ui ) {
				$( "#amount3" ).val( ui.value + " SMS");
			}
		});	
		$( "#amount3" ).val($( "#slider-range-min3" ).slider( "value" ) + " SMS" );

	});
	</script>
  
<!--[if IE 7]>	
<link rel="stylesheet" type="text/css" media="all" href="/wcsstore/AuroraStorefrontAssetStore/css/ie7.css" />
<![endif]-->

</head>
<body class="has-js">

<div id="template">
<div class="main">

 <!-- start .dummy_header -->
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
 <div class="tm_telemedia_navigation_left_menu">
	<ul>
	<li><a href="#" class="tm_border">calculate monthly usage</a></li>
	<li><a href="#" class="tm_active tm_border1 ">monthly rental plan</a></li>
	</ul>
	</div>
	<div class="clear"></div>
<div class="clear"></div>
 <!-- 918px --> 
 <div class="topatab1">
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active" id="select_rent">select a <span>monthly rent</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">claim your <span>freebies</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">add <span>boosters</span></td>
         <td width="25%" height="63" align="center" valign="middle" class="uitab_active">select <span>number</span></td>
       </tr>
     </table>

   </div>
   <div class="one-liner">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
   <div class="clear"></div>
 <div class="popup-inner">
 	<!-- Left -->
	<div class="abascus-left"><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/abascus_bg.jpg" /></div>
	<!-- Left end -->
	
	<!-- middle -->
	<div class="abascus-middle">
		<div class="abascus-slider">
			<p>
			<input type="text" id="amount" />
			</p>
			<div id="slider-range-min" class="orange"></div>
		</div>

		<div class="abascus-slider">
			<p>
			<input type="text" id="amount1" />
			</p>
			<div id="slider-range-min1" class="voilet"></div>
		</div>

		<div class="abascus-slider">
			<p>
			<input type="text" id="amount2" />
			</p>
			<div id="slider-range-min2" class="green"></div>
		</div>

		<div class="abascus-slider">
			<p>
			<input type="text" id="amount3" />
			</p>
			<div id="slider-range-min3" class="isd"></div>
		</div>
	</div>
	<!-- middle end -->
	
	<!-- Right -->
	<div class="abascus-right"><img src="/wcsstore/AuroraStorefrontAssetStore/images/images/abascus_bg.jpg" /></div>
	<!-- right end -->
	<div class="clear"></div>
	<!-- bottom -->
	<wcf:url var="GetPostpaidPlansCmd" value="GetPostpaidPlansCmd">
		<wcf:param name="langId" value="${langId}" />
		<wcf:param name="storeId" value="${storeId}" />
		<wcf:param name="catalogId" value="${catalogId}" />
		<wcf:param name="freebiesCount" value="4" />
		<wcf:param name="catalogIdentifier" value="${catalogIdentifier}" />
	</wcf:url>
	<div class="abascus-bottom">
		<div class="abascus-bottom-continue"><a href="${GetPostpaidPlansCmd}">continue</a></div>
	</div>
	<!-- bottom end -->
	
	
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
				alert("Error: "+e.message);
			}
	})
</script>

</body>
</html>
