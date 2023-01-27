import { configureStore, createSlice } from "@reduxjs/toolkit";
import user from "./reducer/userEdit";

export default configureStore({
  reducer: {
    user: user.reducer,
  },
});
