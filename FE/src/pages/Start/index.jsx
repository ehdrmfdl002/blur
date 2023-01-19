import { useState } from "react";
import Header from "../../components/Header";
import "./index.css";
import SignIn from "./SignIn/signIn";
import SignInWrap from "./SignIn/signInWrap";
import SignUp from "./SignUp/signUp";

function Start() {
  const [signInModal, setSignInModal] = useState(false);
  const showSignInModal = () => {
    setSignInModal((pre) => !pre);
  };

  const [signUpModal, setSignUpModal] = useState(false);
  const showSignUpModal = () => {
    setSignUpModal((pre) => !pre);
  };

  return (
    <div className="Start">
      {signInModal && !signUpModal ? (
        <SignIn
          showSignUpModal={showSignUpModal}
          showSignInModal={showSignInModal}
        />
      ) : null}
      {signInModal || signUpModal ? (
        <SignInWrap
          signInModal={signInModal}
          signUpModal={signUpModal}
          showSignInModal={showSignInModal}
          showSignUpModal={showSignUpModal}
        />
      ) : null}
      {signUpModal && !signInModal ? (
        <SignUp
          showSignUpModal={showSignUpModal}
          showSignInModal={showSignInModal}
        />
      ) : null}
      <Header />
      <div className="SubFrame">
        <div className="SubLeftDiv">
          <span className="SubLeftTitle">Hey, Just Blur!</span>
          <span className="SubLeftDesc">Show me your own color.</span>
          <div className="CommBoxFrame1" onClick={showSignInModal}>
            <span className="CommBoxFrameStart">Start</span>
          </div>
        </div>

        <div className="SubRightDiv">
          <div className="SubRightImg"></div>
        </div>
      </div>
    </div>
  );
}

export default Start;
