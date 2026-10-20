<script setup lang="ts">
import logo from '@/assets/logo.svg'

const showDeveloping = () => {
  ElMessageBox.alert('Developing...', 'Notice', {
    confirmButtonText: 'OK',
    type: 'info',
  })
}
</script>

<template>
  <el-affix>
    <header class="nav">
      <div class="nav-inner">
        <div style="position: relative; width: 100%">
          <ul class="nav-list">
            <li class="logoItem">
              <a href="/home" class="logoLink">
                <img :src="logo" alt="logo" style="height: 100%" />
              </a>
            </li>
            <li class="rightItemList">
              <ul class="itemList">
                <li class="item">
                  <!--没有其它按钮，暂时占位 -->
                  <div class="placeholder"></div>
                </li>
                <li class="item">
                  <router-link to="/home" class="anchor">Home</router-link>
                </li>
                <li class="item">
                  <router-link to="/seize" class="anchor">Seize</router-link>
                </li>
              </ul>
              <div style="flex: 1"></div>
              <div class="navDivider"></div>
              <div style="flex: 1"></div>
              <ul class="loginList">
                <li class="loginItem">
                  <el-tooltip effect="dark" content="Developing..." placement="top">
                    <button class="anchor" @click="showDeveloping">Log in</button>
                  </el-tooltip>
                </li>
                <li class="loginItem">
                  <el-tooltip effect="dark" content="Developing..." placement="top">
                    <button class="anchor signupBtn" @click="showDeveloping">Sign up</button>
                  </el-tooltip>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </header>
  </el-affix>
</template>

<style scoped lang="scss">
.nav {
  height: var(--nav-height);
  width: 100%;
  background: linear-gradient(
    180deg,
    var(--header-bg) 0%,
    color-mix(in oklab, var(--header-bg) 100%, transparent 5%) 100%
  );
  border-bottom: solid 1px var(--header-border);
}

.nav-inner {
  height: var(--nav-height);
  width: 100%;
  max-width: var(--content-max-width);
  margin-inline: auto; /* 水平居中 */
  padding-left: var(--page-padding);
  padding-right: var(--page-padding);
  display: flex;
  align-items: center; /* 垂直居中 */
}

.nav-list {
  min-height: var(--nav-height);
  align-items: center;
  gap: 8px;
  display: flex;
}

.logoItem {
  color: var(--color-text-primary);
  flex: 1;
  justify-content: flex-start;
  display: flex;
}

.logoLink {
  border-radius: 6px;
  justify-content: flex-start;
  align-items: center;
  height: 32px;
  padding: 0 8px;
  display: flex;
}

.rightItemList {
  flex-shrink: 0;
  align-items: center;
  width: 100%;
  max-width: 620px;
  margin: 0;
  display: flex;
}

.itemList {
  align-items: center;
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
}

.loginList {
  justify-content: flex-end;
  align-items: center;
  gap: 8px;
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
}

.item {
  list-style: none;
  display: grid;
}

.loginItem {
  justify-content: center;
  align-items: center;
  display: flex;
}

.placeholder {
  height: 32px;
  width: 324px;
}

.navDivider {
  background: var(--color-border-primary);
  width: 1px;
  height: 16px;
  margin-inline: 8px;
}

.anchor {
  height: 32px;
  border-radius: var(--radius-rounded);
  transition: var(--speed-quickTransition) var(--ease-out-quad);
  background: 0 0;
  justify-content: center;
  align-items: center;
  padding: 0 12px;
  font-size: 13px;
  transition-property: color, background;
  display: flex;
  position: relative;
  &:hover,
  &.router-link-active {
    color: var(--color-text-primary);
    background: var(--anchor-glass-bg);
  }
}

.signupBtn {
  background: #e5e5e6;
  &:hover {
    color: #000;
    background: #fff;
  }
}
</style>
