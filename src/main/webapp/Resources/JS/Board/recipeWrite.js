function validateForm(form) {  // 필수 항목 입력 확인
    if (form.recipeTitle.value == "") {
        alert("제목을 입력하세요.");
        form.recipeTitle.focus();
        return false;
    }
    for(let i=1; i<=step; i++) {
		const content = form[`content_${i}`].value;
		if(content.trim() === "") {
			if(i==1) { alert(`재료를 입력하세요`); }
			else{ alert(`단계 ${i-1}의 내용을 입력하세요.`); }
			form[`content_${i}`].focus();
			return false;
		}
	}
	return true;    
}

let step = 1;
function addStep() {
	if(step < 10) {
		step++;
		const table = document.getElementById("recipeDetail");
		const newRow = table.insertRow();
		newRow.innerHTML = `
			<td>${step}</td>
			<td><textarea name="content_${step}" style="width:90%;height:100px;"></textarea></td>
			<td><input type="file" name="imgOName_${step}"/></td>
			<td><button type="button" onClick="removeStep(this);"> - </button></td>
		`;
	} else {
		alert("더 이상 추가할 수 없습니다.");
	}
}

function removeStep(button) {
    const row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
    step--;
}
