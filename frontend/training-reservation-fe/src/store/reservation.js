import { createSlice } from '@reduxjs/toolkit';

const initialReservationState = {
  reserved: false,
  date: []
};

const reserveSlice = createSlice({
  name: 'reservation',
  initialState: initialReservationState,
  reducers: {
    reservation(state, action) {
      state.reserved = true
      state.date = [...state.date, action.payload]
    },
    canecllation(state) {
      state.reserved = false
    },
  },
});

export const reservActions = reserveSlice.actions;

export default reserveSlice.reducer;