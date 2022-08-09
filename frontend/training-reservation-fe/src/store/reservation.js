import { createSlice } from '@reduxjs/toolkit';

const initialReservationState = {
  reserved: false,
  date: [],
  dateTime: []
};

const reserveSlice = createSlice({
  name: 'reservation',
  initialState: initialReservationState,
  reducers: {
    reservation(state, action) {
      state.reserved = true
      state.date = [...state.date, action.payload.date]
      state.dateTime = [...state.dateTime, action.payload.dateTime]
    },
    canecllation(state) {
      state.reserved = false
    },
  },
});

export const reservActions = reserveSlice.actions;

export default reserveSlice.reducer;