import "./index.css";

function Main() {
  return (
    <div className="SubFrame">
      <div className="SubLeftDiv">
        <span className="SubLeftTitle">Hey, Just Blur!</span>
        <span className="SubLeftDesc">Show me yot own color.</span>
        <button id="signInButton" className="CommBoxFrame1">
          <span className="CommBoxFrameStart"> Start</span>
        </button>
      </div>

      <div className="SubRightDiv">
        <div className="SubRightImg"></div>
      </div>
    </div>
  );
}

export default Main;
