import { createSlice } from '@reduxjs/toolkit';

const initialAuthState = {
  isAuthenticated: false,
  role: 'ROLE_GUEST',
  expirationDate: ''
};

const authSlice = createSlice({
  name: 'authentication',
  initialState: initialAuthState,
  reducers: {
    login(state, action) {
      state.isAuthenticated = true;
      state.role = action.payload.role;
      state.expirationDate = action.payload.expirationDate
    },
    logout(state) {
      state.isAuthenticated = false;
      state.role = 'ROLE_GUEST'
    },
  },
});

export const authActions = authSlice.actions;

export default authSlice.reducer;