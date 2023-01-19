import Header from "../../components/Header";
import Main from "../../components/Main";
import SignIn from "./signIn";
import { useState, useRef, useEffect } from "react";

function Home() {
  const [modalOn, setmodalOn] = useState(false);
  const node = useRef();

  useEffect(() => {
    const clickOustside = (e) => {
      if (modalOn && !node.current.contains(e.target)) {
        setmodalOn((pre) => !pre);
      }
    };

    const showModal = () => {
      console.log("i'm clicked");
      console.log(modalOn);
      setmodalOn(true);
      console.log(modalOn);
    };

    console.log(modalOn);

    document.addEventListener("mousedown", clickOustside);
    const btn = document.getElementById("signInButton");
    btn.addEventListener("click", showModal);

    return () => {
      btn.removeEventListener("click", showModal);
      document.removeEventListener("mousedown", clickOustside);
    };
  }, [modalOn]);

  return (
    <div className="Home">
      <Header />
      <Main />
      {modalOn ? (
        <div style={{ width: "1px", height: "1px" }} ref={node}>
          <SignIn />
        </div>
      ) : null}
    </div>
  );
}

export default Home;
