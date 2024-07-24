/**
 * ユーザー画面一覧
 */
$(function(){
	//テーブルの行をクリックしたときの処理
	$('#comReading tbody tr input').on('click',function(){
		
		// ログインID一時保管
		editSelectedLoginId($(this).closest('tr'));
	});
 });
 
 /**
 * テーブルで選択された行のログインIDを画面のhidden要素に保管します
 * 
 * @param row 選択された行情報
 */
 function editSelectedLoginId(row){
	 row.find('td').each(function(){
		 var columnId = $(this).attr('id');
		 if(columnId.startsWith('loginId_')){
			 $('#selectedLoginId').val($(this).text());
			 return false;
		 }
	 });
 }
 
 
 