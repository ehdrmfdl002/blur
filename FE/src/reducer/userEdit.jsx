import { createSlice } from "@reduxjs/toolkit";

const user = createSlice({
  name: "user",
  initialState: { name: "kim", age: 26 },
  reducers: {
    changeName(state) {
      state.name = "parkdd";
    },
  },
});

export const { changeName } = user.actions;

export default user;
