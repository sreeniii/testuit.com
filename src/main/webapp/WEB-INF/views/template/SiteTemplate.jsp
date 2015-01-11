<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Testuit</title>
        <link type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
		<link type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap-responsive.min.css' />" rel="stylesheet">
		<link type="text/css" href="<c:url value='/resources/css/theme.css' />" rel="stylesheet">
		<link type="text/css" href="<c:url value='/resources/images/icons/css/font-awesome.css' />" rel="stylesheet">
		<link type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600' rel='stylesheet'>
		
		<script src="<c:url value='/resources/scripts/jquery-1.9.1.min.js' />" type="text/javascript"></script>
		<script src="<c:url value='/resources/scripts/jquery-ui-1.10.1.custom.min.js' />" type="text/javascript"></script>
		<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/resources/scripts/flot/jquery.flot.js' />" type="text/javascript"></script>
        <script src="<c:url value='/resources/scripts/flot/jquery.flot.resize.js' />" type="text/javascript"></script>
        <script src="<c:url value='/resources/scripts/datatables/jquery.dataTables.js' />" type="text/javascript"></script>
        <script src="<c:url value='/resources/scripts/common.js' />" type="text/javascript"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <!-- /navbar -->
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <tiles:insertAttribute name="menu" />
                    <!--/.span3-->
                    <div class="span9">
                        <div class="content">
                            <tiles:insertAttribute name="body" />
                        </div>
                        <!--/.content-->
                    </div>
                    <!--/.span9-->
                </div>
            </div>
            <!--/.container-->
        </div>
        <!--/.wrapper-->
        <tiles:insertAttribute name="footer" />
    </body>
