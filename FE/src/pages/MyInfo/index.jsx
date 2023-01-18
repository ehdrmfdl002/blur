import "../../App.css";
import "./index.css";

function MyInfo() {
  return (
    <div>
      <div className="MIProfileImgDiv">
        <div className="MIProfilefictureSet">
          <div className="MIProfileEditProfileAdd"></div>
          <span className="MIProfileEditProfileText">사진 설정</span>
        </div>
      </div>

      <span className="MIProfileHashTag">Hash Tag</span>

      <div className="MIProfileSetting">
        <div className="MIProfileSettingIcon">
          <div className="MiProfileSettingback"></div>
          <span className="MIProfileSettingText">설정하기</span>
        </div>
      </div>
      <div className="MIProfileNameAge">
        <span className="MIProfileName">김블리</span>
        <span className="MIProfileAge">26</span>
      </div>
      <span className="MIProfileEditProfile">profile edit</span>
    </div>
  );
}

export default MyInfo;
