<template>
  <router-view />
</template>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.app-bar {
  background-color: #6FB6DE !important;
}

.hamburger.dark .line,
.nav-button.dark .line {
  background-color: #fff !important;
}

.fg-custom {
  color: #fff !important;
}

.panel {
  border: 1px solid #0093CB !important;
  color: #41464C !important;
}

.panel .panel-title {
  border-bottom: 1px solid #0093CB !important;
}

.panel .panel-title .icon {
  border-right: 1px solid #0093CB !important;
}

.input {
  border: 1px solid #0093CB !important;
}

.input .prepend {
  width: 130px;
  /* text-align: right!important; */
  background-color: #538ee662;
  color: #41464C;
}

.button {
  color: #41464C !important;
}

button {
  cursor: pointer;
  border: none;
}

.table thead tr th {
  text-align: center;
}

h4 {
  text-align: left;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.valuebox {
  margin-left: 5px;
  margin-right: 5px;
  width: 100px;
  text-align: right;
}

.vchange {
  width: 25px;
}

.app-bar .app-bar-container>.d-menu, .app-bar .app-bar-container>.v-menu {
    top: 100%;
    right: 0px;
}

.app-bar a {
    cursor: pointer;
  }

.docs-content {
  height: auto !important;
  margin-top: 60px;
}

body {
    min-height: calc(100vh - 60px);
}
</style>

<script>

export default {
  name: 'App',
  mounted: function () {
     //Metro.init();
  },
}
</script>