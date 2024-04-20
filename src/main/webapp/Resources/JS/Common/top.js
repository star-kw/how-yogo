document.addEventListener('DOMContentLoaded', function() {
  const recipeBoard = document.querySelector('.recipe_board');
  const recipeMenu = document.querySelector('.recipe_menu');

  // 요소에 마우스가 진입하면 메뉴를 표시하고 위치를 조정함
  recipeBoard.addEventListener('mouseenter', function() {
    // 요소 아래에 메뉴 표시
    recipeMenu.style.display = 'flex';
  });

  // 요소를 떠날 때 메뉴를 숨김
  recipeBoard.addEventListener('mouseleave', function() {
    recipeMenu.style.display = 'none';
  });
});
