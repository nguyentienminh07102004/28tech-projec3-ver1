<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:set var="buildingListURL" value="/admin/building-list" />
<html>
<head>
    <title>Danh sách toà nhà</title>
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
                <li class="active">Danh sách toà nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 col-sm-12 widget-container-col ui-sortable">
                    <div class="widget-box ui-sortable-handle">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm kiếm</h5>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="font-family: JetBrains Mono;">
                            <div class="widget-main">
                                <form:form class="form-horizontal" role="form" id="listForm" method="GET" action="${buildingListURL}" modelAttribute="buildingSearch">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label for="name">Tên toà nhà</label>
                                                    <form:input  type="text" class="form-control" path="name" />
                                                </div>
                                                <div class="col-xs-6">
                                                    <label for="floorArea">Diện tích sàn</label>
                                                    <form:input type="number" class="form-control" path="floorArea" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label for="district">Quận</label>
                                                    <form:select class="form-control" path="district">
                                                        <c:forEach var="item" items="${district}">
                                                            <form:option value="${item.key}">${item.value}</form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="ward">Phường</label>
                                                    <form:input type="text" class="form-control" path="ward" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="street">Đường</label>
                                                    <form:input type="text" path="street" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label for="numberOfBasement">Số tầng hầm</label>
                                                    <form:input path="numberOfBasement" type="number" min="0" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label for="direction">Hướng</label>
                                                    <form:input path="direction" type="text" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label for="level">Hạng</label>
                                                    <form:input path="level" type="text" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label for="areaFrom">Diện tích từ</label>
                                                    <form:input path="areaFrom" type="number" min="0" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="areaTo">Diện tích đến</label>
                                                    <form:input path="areaTo" type="number" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="rentPriceFrom">Giá thuê từ</label>
                                                    <form:input path="rentPriceFrom" type="number" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="rentPriceTo">Giá thuê đến</label>
                                                    <form:input path="rentPriceTo" type="number" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label for="managerName">Tên quản lý</label>
                                                    <form:input path="managerName" type="text" class="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="managerPhoneNumber">SĐT Quản lý</label>
                                                    <form:input path="managerPhoneNumber" type="text" class="form-control" />
                                                </div>
                                                <div class="col-xs-2">
                                                    <label for="staffId">Nhân viên phụ trách</label>
                                                    <form:select class="form-control" path="staffId" >
                                                        <form:option value="">--- Chọn nhân viên ---</form:option>
                                                        <form:options items="${listStaffs}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes path="typeCode" items="${typeCode}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5"></div>
                                                <div class="col-xs-6">
                                                    <button class="btn btn-info" style="text-align: center;">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                        </svg>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>

                        <div class="col-xs-12">
                            <div class="col-xs-11"></div>
                            <a class="btn btn-info" title="Thêm toà nhà" href="/admin/building-edit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-fill-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </a>
                            <button class="btn btn-danger" title="Xoá toà nhà" onclick="deleteBuildings()">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- bảng danh sách toà nhà -->
            <div class="row" style="margin-top: 30px;">
                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên toà nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lý</th>
                            <th>SĐT quản lý</th>
                            <th>Diện tích sàn</th>
                            <th>Diện tích trống</th>
                            <th>Diện tích thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="item" items="${buildingList}">
                                <tr>
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.numberOfBasement}</td>
                                    <td>${item.managerName}</td>
                                    <td>${item.managerPhone}</td>
                                    <td>${item.floorArea}</td>
                                    <td>${item.emptyArea}</td>
                                    <td>${item.rentArea}</td>
                                    <td>${item.serviceFee}</td>
                                    <td>${item.brokerageFee}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success" title="Giao toà nhà cho nhân viên quản lý" onclick="assignmentBuilding(${item.id})">
                                                <i class="ace-icon fa fa-check bigger-120"></i>
                                            </button>
                                            <a class="btn btn-xs btn-info" title="Cập nhật toà nhà" href="/admin/building-edit-${item.id}">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>
                                            <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<div class="modal fade" role="dialog" id="assignmentBuildingModel">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover center" id="staffList">
                    <thead>
                    <tr>
                        <th style="text-align: center;"> Chọn</th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                    <input id="buildingId" type="hidden" />
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btn-assignmentBuilding">Giao toà nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    const assignmentBuilding = (buildingId) => {
        $("#assignmentBuildingModel").modal();
        $("#buildingId").val(buildingId);
        loadStaffs(buildingId);
    }
    const loadStaffs =(buildingId) => {
        $.ajax({
            url: "/api/building/" + buildingId + "/staffs",
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            success: (respond) => {
                let data = "";
                let staffs = respond.data;
                staffs.forEach(item => {
                    data += "<tr>";
                    data += "<td>";
                    data += `<input type="checkbox" value="` + item.staffId + `" " ` + item.checked + ` />`;
                    data += "</td>";
                    data += "<td>";
                    data += item.fullName;
                    data += "</td>";
                    data += "</tr>";
                });
                $("#staffList tbody").html(data);
            },
            error: (respond) => {

            }
        })
    }
    const btnAssignmentBuilding = document.querySelector("#btn-assignmentBuilding");
    btnAssignmentBuilding.addEventListener("click", (evt) => {
        evt.preventDefault();
        let data = {};
        data["buildingId"] = $("#buildingId").val();
        let staff = [];
        const staffNode = document.querySelector("#staffList").querySelectorAll("input[type=checkbox]:checked");
        staffNode.forEach(item => staff.push(item.value));
        data["staffId"] = staff;
        if(staff.length > 0) {
            sendAssignment(data);
        }
    });
    const sendAssignment = (data) => {
        $.ajax({
            url: "/api/building/assignment",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: (response) => {
                console.log(response);
            },
            error: (response) => {
                console.log(response);
            }
        });
    }
    let deleteBuilding = (buildingId) => {
        let building = [buildingId];
        deleteBuildingAPI(building);
    };
    function deleteBuildings() {
        const building = document.querySelector("#simple-table").querySelectorAll("input[type = checkbox]:checked");
        let buildings = [];
        building.forEach(item => buildings.push(item.value));
        deleteBuildingAPI(buildings);
    }
    function deleteBuildingAPI(data) {
        $.ajax({
            url: "/api/building/",
            type: "DELETE",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: (res) => {
                console.log("Success");
            },
            error: (res) => {

            }
        });
    }

</script>
</body>
</html>
