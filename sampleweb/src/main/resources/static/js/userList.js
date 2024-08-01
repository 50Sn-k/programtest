/**
 * ユーザー画面一覧
 */
$(function(){
	//テーブルの行をクリックしたときの処理
	$('#userList tbody tr').on('click',function(){
		//すべての行の選択状態を解除
		$('#userList tbody tr').removeClass();
		//クリックされた行に選択状態のクラスを追加
		$(this).addClass('table-danger');
		//更新ボタン、削除ボタンを活性化
		$('#editBtn').removeAttr('disabled');
		$('#deleteDummyBtn').removeAttr('disabled');
		
		// ログインID一時保管
		editSelectedLoginId($(this));
	});
	
	$('#caseStatusList tbody tr').on('click',function(){
		//すべての行の選択状態を解除
		$('#caseStatusList tbody tr').removeClass();
		//クリックされた行に選択状態のクラスを追加
		$(this).addClass('table-danger');
		//更新ボタン、削除ボタンを活性化
		$('#editBtn').removeAttr('disabled');
		$('#deleteDummyBtn').removeAttr('disabled');
		$('#memberBtn').removeAttr('disabled');
		
		// ログインID一時保管
		editSelectedCaseId($(this));
	});
	$('#deleteOkBtn').click(function(){
		$('#deleteBtn').trigger('click');
	});
	$('#memberBtn').click(function(){
		$('#memberSearchBtn').trigger('click');
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
 
  /**
 * テーブルで選択された行のログインIDを画面のhidden要素に保管します
 * 
 * @param row 選択された行情報
 */
 function editSelectedCaseId(row){
	 row.find('td').each(function(){
		 var columnId = $(this).attr('id');
		 if(columnId.startsWith('caseId_')){
			 $('#selectedCaseId').val($(this).text());
			 return false;
		 }
	 });
 }
 
 