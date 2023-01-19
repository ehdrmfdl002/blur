import "./signIn.css";

function SignIn({ showSignUpModal, showSignInModal }) {
  return (
    <div className="SIModal">
      <div className="SIModalHeader">
        <span className="SIModalHeaderText">Sign In</span>
      </div>
      <div>
        <form>
          <label htmlFor="user_id"></label>
          <input id="user_id" placeholder="ID를 입력해 주세요"></input>
          <label htmlFor="user_pw"></label>
          <input id="user_pw" placeholder="PW를 입력해 주세요"></input>
        </form>
        <button>로그인</button>
        <button>아이디 저장</button>
        <button
          onClick={() => {
            showSignInModal();
            showSignUpModal();
          }}
        >
          회원가입
        </button>
        <button>비밀번호 찾기</button>
      </div>
      <div>
        <button className="KakaoLoginBtn">카카오로 로그인</button>
        <button className="NaverLoginBtn">네이버로 로그인</button>
      </div>
    </div>
  );
}

export default SignIn;
