/**
 * 注册店铺 js
 * 1. 页面初始化时解析后台传来的 areaList 和 shopCategoryList
 * 2. 异步提交表单
 */
$(function () {
    var shopInitUrl = '/o2o/shop/infoInit';
    getShopInfo();
    function getShopInfo() {
        $.getJSON(shopInitUrl, function (data) {
            if (data.success){
                var tempShopCategoryHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempShopCategoryHtml += '<option data-id="' + item.shopCategoryId + '">' +
                        item.shopCategoryName + '</option>'
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' +
                        item.areaName + '</option>'
                });
                $('#shop-category').html(tempShopCategoryHtml);
                $('#area').html(tempAreaHtml);
            }else{
                $.toast('初始化店铺类别和区域信息失败！' + data.errMsg);
            }
        })
    }

    var registerShopUrl = '/o2o/shop/registerShop';
    $('#submit').click(function () {
       var shop = {};
       shop.shopName = $('#shop-name').val();
       shop.shopDesc = $('#shop-desc').val();
       shop.shopAddr = $('#shop-addr').val();
       shop.phone = $('#shop-phone').val();
       shop.shopCategory = {
           shopCategoryId : $('#shop-category').find('option').not(function () {
               return !this.selected;
           }).data('id')
       };
       shop.area = {
           areaId : $('#area').find('option').not(function () {
               return !this.selected;
           }).data('id')
       };
       var shopImg = $('#shop-img')[0].files[0];
       var formData = new FormData();
       formData.append('shopImg', shopImg);
       formData.append('shopStr', JSON.stringify(shop));
       $.ajax({
           url:registerShopUrl,
           type:'POST',
           data:formData,
           contentType:false,
           processData:false,
           cache:false,
           success:function (data) {
               if (data.success){
                   $.toast('提交成功');
               }else {
                   $.toast('提交失败！' + data.errMsg);
               }
           }
       });
    });
    
    function changeVerifyCode() {
        
    }
    
})