/**
 * 店铺信息展示、修改、管理页面 js
 */
$(function () {
    var shopListUrl = '/o2o/shop/getShopList';
    var shopManageUrl = '/o2o/shop/getShopManagementInfo';
    getList();
    function getList() {
        $.ajax({
            url:shopListUrl,
            type:'GET',
            dataType:'json',
            success:function (data) {
                if (data.success){
                    handleList(data.shopList);
                    handleUser(data.user);
                }else {
                    $.toast(data.errMsg);
                }
            }
        })
    }

    // function handleList(data) {
    //     var html = '';
    //     data.map(function (item, index) {
    //         html += '<div class="row row-shop"><div class="col-40">' +
    //             item.shopName + '</div><div class="col-40">' +
    //             shopStatus(item.enableStatus) + '</div><div class="col-20">' +
    //             goShopManagement(item.enableStatus, item.shopId) + '</div></div>';
    //     })
    //     $('.shop-wrap').html(html);
    // }

    var pattern = /\//

    function handleList(data) {
        var html = '<ul>';
        data.map(function (item, index) {
            html += '<li><a href="'+ goShopManagement(item.enableStatus, item.shopId) +'" class="item-link item-content">' +
                '<div class="item-media"><img src="/o2o/common/getImage?imageUrl=' + item.shopImg.replace(/\\/g, '/') +
                '" width="80"></div>' +
                '<div class="item-inner"><div class="item-title-row"><div class="item-title">' + item.shopName +
                '</div><div class="item-after">' + shopStatus(item.enableStatus) +
                '</div></div><div class="item-subtitle">' + item.shopAddr +
                '</div><div class="item-text">' + item.shopDesc +
                '</div> </div></a></li>';
        });
        html += "</ul>";
        $('.shop-list').html(html);

    }

    function shopStatus(status) {
        if (status == -1) {
            return "店铺非法";
        }else if (status == 0){
            return "审核中";
        } else if (status == 1){
            return "审核通过";
        }
    }

    // function goShopManagement(status, shopId) {
    //     if (status == 1){
    //         return '<a href="/o2o/shop/getShopManagementInfo?shopId=' + shopId + '">进入管理</a>';
    //     }else{
    //         return '';
    //     }
    // }
    function goShopManagement(status, shopId) {
        if (status == 1){
            return '/o2o/shop/management?shopId=' + shopId;
        }else{
            return '#';
        }
    }

    function handleUser(data) {
        $('#user-name').text(data.userName);
    }
})