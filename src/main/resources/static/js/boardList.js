/**
 * 
 */
 
let kind=$("#kind").attr("title"); 
$(".p").click(function() {
	let curPage = $(this).attr("title");
	console.log(kind);
	$("#curPage").val(curPage);
	$("#frm").submit();

});