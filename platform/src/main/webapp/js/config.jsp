<%@ page import="com.code.util.Config" %>
<%@ page contentType="text/javascript;charset=UTF-8" language="java" %>
<%
    //Config.refresh();
    Config configProp = Config.getConfig("comm-config.properties");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String title = configProp.getProperty("title");
    String datacopyright = configProp.getProperty("datacopyright");
    String syscopyright = configProp.getProperty("syscopyright");
    String centerLng = configProp.getProperty("centerLng");
    String centerLat = configProp.getProperty("centerLat");
    String centerZoom = configProp.getProperty("centerZoom");
%>
var basePath = "<%=basePath%>";
var title = "<%=title%>";
var dataCopyRight="<%=datacopyright%>";
var sysCopyRight="<%=syscopyright%>";
var centerLng="<%=centerLng%>";
var centerLat="<%=centerLat%>";
var centerZoom="<%=centerZoom%>";

