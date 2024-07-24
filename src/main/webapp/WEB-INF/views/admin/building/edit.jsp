<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:set var="buildingURL" value="/api/building/" />
<html>
<head>
    <title>Thêm toà nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thêm toà nhà
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <form:form id="form-edit" method="POST" class="form-horizontal" modelAttribute="building">
                        <div class="form-group">
                            <label class="col-xs-3">Tên toà nhà</label>
                            <form:input type="text" path="name" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <form:input type="number" path="floorArea" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <form:select path="district" class="col-xs-9">
                                <option value="1">--- Chọn quận ---</option>
                                <form:options items="${district}" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <form:input type="text" path="ward" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <form:input type="text" path="street" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <form:input type="text" path="structure" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <form:input type="number" path="numberOfBasement" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <form:input type="text" path="direction" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <form:input type="text" path="level" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <form:input type="text" path="rentArea" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <form:input type="text" path="rentPrice" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <form:input type="text" path="rentPriceDescription" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <form:input type="text" path="serviceFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <form:input type="text" path="carFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí mô tô</label>
                            <form:input type="text" path="motoFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <form:input type="text" path="overtimeFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <form:input type="text" path="electricityFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <form:input type="text" path="deposit" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <form:input type="text" path="payment" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <form:input type="text" path="rentTime" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <form:input type="text" path="decorationTime" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <form:input type="text" path="managerName" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <form:input type="text" path="managerPhone" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <form:input type="text" path="brokerageFee" class="col-xs-9" />
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Loại toà nhà</label>
                            <div class="col-xs-9">
                                <form:checkboxes path="typeCode" items="${typeCode}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <form:textarea type="text" path="note" class="col-xs-9" />
                        </div>
                        <form:hidden path="id" id="buildingId"/>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="col-xs-3"></div>
                                <div class="col-xs-9">
                                    <button class="btn btn-info" type="submit" id="btn-add-building">
                                        <c:if test="${empty building.id}">Thêm toà nhà</c:if>
                                        <c:if test="${not empty building.id}">Cập nhật toà nhà</c:if>
                                    </button>
                                    <a class="btn btn-info" type="reset" href="/admin/building-list"> Huỷ thao tác </a>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>
    $("#btn-add-building").click((event) => {
        event.preventDefault();
        let data = {};
        let typeCode = [];
        let formEdit = $("#form-edit").serializeArray();
        $.each(formEdit, (index, value) => {
            if(value.name !== "typeCode") {
                data[value.name] = value.value;
            } else {
                typeCode.push(value.value);
            }
        });
        data["typeCode"] = typeCode;
        $.ajax({
            url: "${buildingURL}",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: (respond) => {
                console.log(respond);
            },
            error: (respond, status) => {
                console.log(respond);
                console.log(status);
            }
        });
    });
</script>
</body>
</html>
