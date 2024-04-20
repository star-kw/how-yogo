document.addEventListener('DOMContentLoaded', function() {
    const feedButton = document.getElementById('feedBtn'); // 피드 버튼
    const listButton = document.getElementById('listBtn'); // 목록 버튼
    const feedSection = document.getElementById('feedSection'); // 피드 섹션
    const listSection = document.getElementById('listSection'); // 목록 섹션

    // 피드 형식 버튼 클릭 시 피드 섹션 표시
    feedButton.addEventListener('click', function() {
        feedSection.style.display = 'block';
        listSection.style.display = 'none';
        toggleButton(this);
    });

    // 목록 형식 버튼 클릭 시 목록 섹션 표시
    listButton.addEventListener('click', function() {
        feedSection.style.display = 'none';
        listSection.style.display = 'block';
        toggleButton(this);
    });

    // 버튼 토글 함수
    function toggleButton(clickedButton) {
        const buttons = document.querySelectorAll('.toggle-button');

        buttons.forEach(button => {
            if (button === clickedButton) {
                button.querySelector('img').classList.add('active');
            } else {
                button.querySelector('img').classList.remove('active');
            }
        });
    }
});
