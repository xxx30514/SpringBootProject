const { createApp } = Vue;
import HeaderComponent from './header.js';

const navheader = createApp({
  components: {
    HeaderComponent,
  }
});


navheader.mount('#navheader');