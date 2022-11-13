const qnaList = [
  {
    q: '1. 주기적으로 새로운 친구를 만든다.',
    a: [
      { answer: 'yes' , type: [8, 9, 10, 11, 12, 13, 14, 15] },
      { answer: 'no' , type: [0, 1, 2, 3, 4, 5, 6, 7] },
    ]
  },
  {
    q: '2. 자유 시간 중 상당 부분을 다양한 관심사를 탐구하는 데 할애한다. ',
    a: [
      { answer:'yes' , type: [0, 1, 2, 3, 8, 9, 10, 11] },
      { answer:'no' , type: [4, 5, 6, 7, 12, 13, 14, 15] },
    ]
  },
  {
    q: '3. 다른 사람이 울고 있는 모습을 보면 자신도 울고 싶어질 때가 많다.',
    a: [
      { answer:'yes' , type: [0, 2, 5, 7, 8, 10, 13, 15] },
      { answer: 'no' , type: [1, 3, 4, 6, 9, 11, 12, 14] },
    ]
  },
  {
    q: '4. 일이 잘못될 때를 대비해 여러 대비책을 세우는 편이다.',
    a: [
      { answer:'yes' , type: [0, 1, 4, 5, 10, 11, 14, 15] },
      { answer: 'no' , type: [2, 3, 6, 7, 8, 9, 12, 13] },
    ]
  },
  {
    q: '5. 파티나 행사에서 새로운 사람에게 먼저 자신을 소개하기보다는 주로 이미 알고 있는 사람과 대화하는 편이다.',
    a: [
      { answer:'yes' , type: [8, 9, 10, 11, 12, 13, 14, 15] },
      { answer: 'no' , type: [0, 1, 2, 3, 4, 5, 6, 7 ] },
    ]
  },
  {
    q: '6. 예술 작품의 다양한 해석에 대해 토론하는 일에는 크게 관심이 없다.',
    a: [
      { answer:'yes' , type: [0, 1, 2, 3, 8, 9, 10, 11] },
      { answer: 'no' , type: [4, 5, 6, 7, 12, 13, 14, 15] },
    ]
  },
  {
    q: '7. 압박감이 심한 환경에서도 평정심을 유지하는 편이다.',
    a: [
      { answer:'yes' , type: [0, 2, 5, 7, 8, 10, 13, 15] },
      { answer: 'no' , type: [1, 3, 4, 6, 9, 11, 12, 14] },
    ]
  },
  {
    q: '8. 하나의 프로젝트를 완전히 완료한 후 다른 프로젝트를 시작하는 편이다.',
    a: [
      { answer:'yes' , type: [0, 1, 4, 5, 10, 11, 14, 15] },
      { answer: 'no' , type: [2, 3, 6, 7, 8, 9, 12, 13] },
    ]
  },
  {
    q: '9. 관심이 가는 사람에게 다가가서 대화를 시작하기가 어렵지 않다.',
    a: [
      { answer:'yes' , type: [8, 9, 10, 11, 12, 13, 14, 15] },
      { answer: 'no' , type: [0, 1, 2, 3, 4, 5, 6, 7 ] },
    ]
  },
  {
    q: '10. 결말을 자신의 방식으로 해석할 수 있는 책과 영화를 좋아한다.',
    a: [
      { answer:'yes' , type: [0, 1, 2, 3, 8, 9, 10, 11] },
      { answer: 'no' , type: [4, 5, 6, 7, 12, 13, 14, 15] },
    ]
  },
  {
    q: '11. 지나치게 슬퍼하거나 쉽게 기뻐하는 편이다.',
    a: [
      { answer:'yes' , type: [0, 2, 5, 7, 8, 10, 13, 15] },
      { answer: 'no' , type: [1, 3, 4, 6, 9, 11, 12, 14] },
    ]
  },
  {
    q: '12. 일정이나 목록으로 계획을 세우는 일을 좋아한다.',
    a: [
      { answer:'yes' , type: [0, 1, 4, 5, 10, 11, 14, 15] },
      { answer: 'no' , type: [2, 3, 6, 7, 8, 9, 12, 13] },
    ]
  },
  {
    q: '13. 다른 사람에게 자신이 어떤 사람으로 보일지 걱정하지 않는 편이다.',
    a: [
      { answer:'yes' , type: [8, 9, 10, 11, 12, 13, 14, 15] },
      { answer: 'no' , type: [0, 1, 2, 3, 4, 5, 6, 7 ] },
    ]
  },
  {
    q: '14. 작은 실수로도 자신의 능력이나 지식을 의심하곤 한다.',
    a: [
      { answer:'yes' , type: [0, 2, 5, 7, 8, 10, 13, 15] },
      { answer: 'no' , type: [1, 3, 4, 6, 9, 11, 12, 14] },
    ]
  },
  {
    q: '15. 자신만큼 효율적이지 못한 사람을 보면 짜증이 난다.',
    a: [
      { answer:'yes' , type: [0, 1, 2, 3, 8, 9, 10, 11] },
      { answer: 'no' , type: [4, 5, 6, 7, 12, 13, 14, 15] },
    ]
  },
  {
    q: '16. 하루 일정을 계획하기보다는 즉흥적으로 하고 싶은 일을 하는 것을 좋아한다.',
    a: [
      { answer:'yes' , type: [0, 1, 4, 5, 10, 11, 14, 15] },
      { answer: 'no' , type: [2, 3, 6, 7, 8, 9, 12, 13] },
    ]
  },
]

const infoList = [
  {
    name: 'ISTJ',
    desc: '"현실주의자"	사실을 중시하는 믿음직한 현실주의자입니다.'
  },
  {
    name: 'ISFJ',
    desc: '"수호자"	주변 사람을 보호할 준비가 되어 있는 헌신적이고 따뜻한 수호자입니다.'
  },
  {
    name: 'ISTP',
    desc: '"장인"	대담하면서도 현실적인 성격으로, 모든 종류의 도구를 자유자재로 다루는 장인입니다.'
  },
  {
    name: 'ISFP',
    desc: '"모험가"	항상 새로운 경험을 추구하는 유연하고 매력 넘치는 예술가입니다.'
  },
  {
    name: 'INFJ',
    desc: '"옹호자"	차분하고 신비한 분위기를 풍기는 성격으로, 다른 사람에게 의욕을 불어넣는 이상주의자입니다.'
  },
  {
    name: 'INTJ',
    desc: '"전략가"	모든 일에 대해 계획을 세우며 상상력이 풍부한 전략가입니다.'
  },
  {
    name: 'INFP',
    desc: '"중재자"	항상 선을 행할 준비가 되어 있는 부드럽고 친절한 이타주의자입니다.'
  },
  {
    name: 'INTP',
    desc: '"논리술사"	지식을 끝없이 갈망하는 혁신적인 발명가입니다.'
  },
  {
    name: 'ESTP',
    desc: '"사업가"	위험을 기꺼이 감수하는 성격으로, 영리하고 에너지 넘치며 관찰력이 뛰어난 사업가입니다.'
  },
  {
    name: 'ESFP',
    desc: '"연예인"	즉흥적이고 넘치는 에너지와 열정으로 주변 사람을 즐겁게 하는 연예인입니다.'
  },
  {
    name: 'ESTJ',
    desc: '"경영자"	사물과 사람을 관리하는 데 뛰어난 능력을 지닌 경영자입니다.'
  },
  {
    name: 'ESFJ',
    desc: '"집정관"	배려심이 넘치고 항상 다름 사람을 도울 준비가 되어 있는 성격으로, 인기가 많고 사교성이 높은 마당발입니다.'
  },
  {
    name: 'ENFP',
    desc: '"활동가"	열정적이고 창의적인 성격으로, 긍적적으로 삶을 바라보는 사교적이면서도 자유로운 영혼입니다.'
  },
  {
    name: 'ENTP',
    desc: '"변론가"	지적 도전을 즐기는 영리하고 호기심이 많은 사색가입니다.'
  },
  {
    name: 'ENFJ',
    desc: '"선도자"	청중을 사로잡고 의욕을 불어넣는 카리스마 넘치는 지도자입니다.'
  },
  {
    name: 'ENTJ',
    desc: '"통솔자"	항상 문제 해결 방법을 찾아내는 성격으로, 대담하고 상상력이 풍부하며 의지가 강력한 지도자입니다.'
  },
]
