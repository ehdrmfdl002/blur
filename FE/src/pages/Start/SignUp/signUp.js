import "./signUp.css";

function SignUp({ showSignUpModal, showSignInModal }) {
  const onSubmit = (e) => {
    e.preventDefault();
    console.log("막았다");
  };

  return (
    <div className="SUModal">
      <div className="SUModalHeader">
        <h3 className="SUModalHeaderText">Sign Up</h3>
      </div>
      <form>
        <div className="SUModalInputIdDiv">
          <label className="SUModalInputIdLabel" htmlFor="user_id">
            ID
          </label>
          <input
            className="SUModalInputId"
            id="user_id"
            placeholder="ID를 입력해 주세요"
          ></input>
          <button onClick={(e) => e.preventDefault()}>아이디 중복체크</button>
        </div>
        <div className="SUModalInputPwDiv">
          <label className="SUModalInputPwLabel" htmlFor="user_pw">
            PW
          </label>
          <input
            className="SUModalInputPw"
            id="user_pw"
            placeholder="PW를 입력해 주세요"
          ></input>
        </div>
        <div className="SUModalInputPwChkDiv">
          <label className="SUModalInputPwChkLabel" htmlFor="user_pw_re">
            PW Check
          </label>
          <input
            className="SUModalInputPwChk"
            id="user_pw_re"
            placeholder="PW를 다시 입력해 주세요"
          ></input>
        </div>
        <div className="SUModalInputEmailDiv">
          <label className="SUModalInputEmailLabel" htmlFor="user_email">
            E-mail
          </label>
          <input
            className="SUModalInputEmail"
            id="user_email"
            placeholder="E-mail을 입력해 주세요"
          ></input>
          <button onClick={(e) => e.preventDefault()}>
            이메일로 인증번호 보내기
          </button>
        </div>
        <div className="SUModalInputEmailConfirmDiv">
          <label
            className="SUModalInputEmailConfirmLabel"
            htmlFor="user_email_confirm"
          >
            E-mail 인증번호
          </label>
          <input
            className="SUModalInputEmailConfirm"
            id="user_email_confirm"
            placeholder="인증번호를 입력해 주세요"
          ></input>
          <button onClick={(e) => e.preventDefault()}>인증번호 확인</button>
        </div>

        <button className="SUSignUpBtn" onClick={onSubmit}>
          <span className="SUBtnText">회원가입</span>
        </button>
      </form>

      <button
        className="SUCancleBtn"
        onClick={() => {
          showSignUpModal();
          showSignInModal();
        }}
      >
        <span className="SUCancleBtnText">취소</span>
      </button>
    </div>
  );
}

export default SignUp;
