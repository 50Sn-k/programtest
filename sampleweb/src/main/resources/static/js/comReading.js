/**
 * ユーザー画面一覧
 */
$(function(){
	//テーブルの行をクリックしたときの処理
	$('button').on('click',function(){
		var idx=$(this).closest('tr').children('th:if(0)');
		// ログインID一時保管
		editSelectedLoginId(idx);
	});
 });
 
 /**
 * テーブルで選択された行のログインIDを画面のhidden要素に保管します
 * 
 * @param row 選択された行情報
 */
 function editSelectedLoginId(row){
	/* row.find('td').each(function(){
		 var columnId = $(this).attr('loginid');
		 if(columnId.startsWith('loginId_')){*/
			 $('#selectedLoginId').val($(row).text());
			 return false;
		// }
	// });
 }
 
 
 