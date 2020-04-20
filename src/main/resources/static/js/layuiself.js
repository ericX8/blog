
/*轮播图*/
layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });
    });
//手机端菜单栏
$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
})

//赞赏功能
$('.payWx').popup({
    popup:$('.pay.popup'),
    on:'click',
    position:'top center'

});
//下拉菜单
$('.ui.dropdown').dropdown();

//表单校验
$('.ui.form').form({
    fields: {
        title:{
            identifier:'title',
            rules:[{
                type:'empty',
                prompt:'标题：请输入博客标题'
            }]
        }
    }
});
//集成Markdown编辑器
let contentEditor;
$(function() {
    contentEditor = editormd("md-content", {
        width: "100%",
        height: 800,
        syncScrolling: "single",
        path: "/lib/editormd/lib/"
    });
});
//生成的目录插件
tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
});
$('.toc.button').popup({
    popup:$('.toc-container.popup'),
    on:'click',
    position:'top center'

});

//右下角二维码
$('.wechat').popup({
    popup:$('.wechat-qr'),
    position:'left center'
});
/*//右下角二维码生成插件
var serurl="http://www.ktbear.com";
var url=/!*[[@{/blog/{id}(id=${blog.id}})}]]*!/"";
var qrcode = new QRCode("qrcode", {
    text: serurl+url,
    width: 100,
    height: 100,
    colorDark : "#000000",
    colorLight : "#ffffff",
    correctLevel : QRCode.CorrectLevel.H
});*/
//平滑到顶部
$('#toTop-button').click(function () {
    $(window).scrollTo(0,500);
});
//平滑监测
var waypoint = new Waypoint({
    element: document.getElementById('wayPoints'),
    handler: function(direction) {
        if(direction=='down'){
            $('#tools').show(500);
        }
        else {
            $('#tools').hide(500);
        }
    }
});