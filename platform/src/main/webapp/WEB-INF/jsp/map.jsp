<%@ page import="java.util.UUID" %>
<%@ page import="com.code.util.Config" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    response.addHeader("set-cookie", "sessionid=" + session.getId());

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>stroyMap</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!--[if lt IE 8]>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/dvf.css">
    <link rel="stylesheet" href="plug/leaflet_0.7.7/leaflet.css"/>
    <link rel="stylesheet" href="plug/leaflet_0.7.7/label/leaflet.label.css"/>
    <link rel="stylesheet" href="plug/leaflet_0.7.7/tagfilter/leaflet-easy-button.css"/>
    <link rel="stylesheet" href="plug/leaflet_0.7.7/tagfilter/leaflet-tag-filter-button.css"/>
    <script type="text/javascript" src="js/config.jsp?random=<%=UUID.randomUUID().toString() %>"></script>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script src="plug/leaflet_0.7.7/leaflet-src.js"></script>
    <script src="js/leaflet-dvf.js"></script>
    <!--label-->
    <script type="text/javascript" src="plug/leaflet_0.7.7/label/leaflet.label.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript" src="plug/layer/layer.js"></script>
    <script type="text/javascript">

    </script>
    <style type="text/css">
    </style>

</head>

<body>
    <div id="centerPanel" style="position: absolute;top:0px;right:0px;bottom:0;left:0px; border:1px solid white;">
        <div id="map" style="float:left;width:100%;height:100%;border:0px solid black;display: block;">
        <div id="copyright" class="leaflet-bottom leaflet-right" style="padding:5px 5px 5px 5px;bottom: 0px;border-radius:5px;background: rgba(255, 255, 255, 0.7);display: block">
        </div>
        </div>
    </div>
</body>
</html>

