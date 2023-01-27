import "../../../App.css";
import "./myInfoModal.css";
import React, { useState } from "react";
import Select from "react-select";
import { Range } from "rc-slider";
import Slider from "react-styled-carousel";
import "rc-slider/assets/index.css";
// import ReactDOM from "react-dom";
import { useDispatch, useSelector } from "react-redux";
import { changeName } from "../../../reducer/userEdit";
import "../index";

function MyInfoModal() {
  const [settingModal, setsettingModal] = useState(false);

  //profile 변경
  const [nickName, setNickName] = useState("");
  const [nickedit, setNickEdit] = useState(false);
  const [email, setEmail] = useState("");
  const [emailedit, setEmailEdit] = useState(false);

  const state = useSelector((state) => state);
  const dispatch = useDispatch();
  let nicNamecontent = (
    <div>
      <button
        onClick={() => {
          setNickEdit(true);
        }}
      >
        수정
      </button>
    </div>
  );

  if (nickedit) {
    nicNamecontent = (
      <div className="PMIdInput">
        <input
          type="text"
          value={nickName}
          onChange={(e) => {
            setNickName(e.target.value);
          }}
        />
        <button onClick={() => setNickEdit(false)}>수정완료</button>
      </div>
    );
  }
  //-----------
  let emailContent = (
    <div>
      {email} <button onClick={() => setEmailEdit(true)}>수정</button>
    </div>
  );

  if (emailedit) {
    emailContent = (
      <div className="PMIdInput">
        <input
          type="text"
          value={email}
          onChange={(e) => {
            setEmail(e.target.value);
          }}
        />
        <button onClick={() => setEmailEdit(false)}>수정완료</button>
      </div>
    );
  }
  //------------------

  // const ExampleSlider = () => (
  //   <Slider>
  //     <h1>1</h1>
  //     <h1>2</h1>
  //     <h1>3</h1>
  //     <h1>4</h1>
  //   </Slider>
  // );

  // const Slider = require("rc-slider");
  // const createSliderWithTooltip = Slider.createSliderWithTooltip;
  // const Range = createSliderWithTooltip(Slider.Range);

  // const [profileModal, setProfileModal] = useState(false);

  // const state = useSelector((state) => state);
  // const [nickName, setnickName] = useSelector((state) => {
  //   return state.nickName;
  // });
  // const nickNameHansdler = () => {
  //   setnickName(e.target.value);
  // };

  function SetModal() {
    return (
      <div className="SettingModal">
        <div className="SetModal">
          <span className="SEtLabel">Setting</span>
          <div className="SEtMidModalChangediv">
            <div className="ModalInputBox">
              <span className="SetMidPartnerLable">Partner Gender</span>
              <div className="SetMMPartnerCheckdiv">
                <div className="flexbox">
                  <div>Male</div>
                  <div>Female</div>
                  <div>None</div>
                </div>
              </div>
            </div>

            <div className="ModalInputBox2">
              <span className="SetMidPartnerLable">Distance from partner</span>
              <div className="SetMMPartnerCheckdiv">
                <input
                  type="range"
                  id="a"
                  name="ages"
                  min="10"
                  max="60"
                  step="10"
                ></input>
              </div>
            </div>

            <div className="ModalInputBox3">
              <span className="SetMidPartnerLable">Partner's age group</span>
              <div className="SetMMPartnerCheckdiv">
                <Range
                  min={0}
                  max={80}
                  defaultValue={[0, 80]}
                  // value={value}
                ></Range>
              </div>
            </div>
          </div>
        </div>
        <button
          className="ModalOut"
          onClick={() => {
            dispatch(changeName());
          }}
        >
          <span className="ModalOutText">confirm</span>
        </button>
      </div>
    );
  }
  return (
    <div className="Modal">
      <div className="leftModal">
        <div className="leftModalImg"></div>
        <div className="leftModalNameDiv">
          <span className="leftModalName"> welcome {state.user} </span>
        </div>
        <div className="leftModalbtnDiv">
          <button
            className="leftModalEditbtn"
            onClick={() => {
              setsettingModal(false);
            }}
          >
            <spna className="leftModalProText"> Profile Edit</spna>
          </button>
          <button
            className="leftModalSetbtn"
            onClick={() => {
              setsettingModal(true);
            }}
          >
            <spna className="leftModalSetText"> Setting</spna>
          </button>
        </div>
      </div>

      <div className="ProfileModal">
        <span className="PMLabel">Profile Edit</span>
        <div className="PMIdDiv">
          <span className="PMIdLable">NickName</span>
          <div className="PMIdInput">{nicNamecontent}</div>
        </div>
        <div className="PMAge">
          <span className="PMAgeLabel">Age</span>
          <select className="PMAgeSelect">
            <option> 알고리즘 작성 </option>
            <option> ㅇㅈ </option>
            <option> 20 </option>
            <option> 55 </option>
          </select>
        </div>
        <div className="PMMBTI">
          <span className="PMMBTILabel">MBTI</span>
          <select className="PMMBTISelect">
            <option> 모름 </option>
            <option> INTJ</option>
            <option> INTP </option>
            <option> ENTJ </option>
            <option> ENTP</option>

            <option> INFJ </option>
            <option> INFP </option>
            <option> ENFJ </option>
            <option> ENFP </option>

            <option> ISTJ</option>
            <option> ISFJ</option>
            <option> ESTJ</option>
            <option> ESFJ </option>

            <option> ISTP</option>
            <option> ISFP </option>
            <option> ESTP </option>
            <option> ESFP </option>
          </select>
        </div>
        <div className="PMMEmail">
          <span className="PMMEmailLabel">E-mail</span>
          <div className="PMMEmailInput">{emailContent}</div>
        </div>

        <div className="PMMGender">
          <span className="PMMGenderLable">Gender</span>
          <div className="PMMGenderdiv">
            <div className="PMMGenderMale">
              <input
                type="checkbox"
                id="subscribeNews"
                name="subscribe"
                value="newsletter"
              />
              <label for="subscribeNews">Male</label>
            </div>
            <div className="PMMGenderFemale">
              <input
                type="checkbox"
                id="subscribeNews"
                name="subscribe"
                value="newsletter"
              />
              <label for="subscribeNews">Femail</label>
            </div>
          </div>
          <div className="PMIntroducing">
            <span className="PMIntroducingLabel">Introducing</span>
            <input className="PMIntroducingInput"></input>
          </div>
        </div>
      </div>
      <button
        className="ModalOut"
        // onMouseDown={() => {
        //   if (setMyInfoModal === true) {
        //     showMyinfoModal(false);
        //   }
        // }}
      >
        <span className="ModalOutText">confirm</span>
      </button>
      {settingModal == true ? <SetModal style></SetModal> : null}
      {/* {profileModal == true ? <setProfileModal style></setProfileModal> : null} */}
    </div>
  );
}

export default MyInfoModal;
// ReactDOM.render(<ExampleSlider />, document.getElementById("root"));
