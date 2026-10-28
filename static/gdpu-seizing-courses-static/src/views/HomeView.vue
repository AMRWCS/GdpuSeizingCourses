<script setup lang="ts"></script>

<template>
  <div class="home">
    <!-- Hero 区 -->
    <div class="hero">
      <h1 class="title">GSC</h1>
      <div class="subtitle">
        <p style="width: 100%; text-align: left">A powerful tool to help you seize courses -</p>
        <p style="width: 100%; text-align: right">make course-seizing faster.</p>
      </div>
      <div class="cta">
        <p>- [ Click</p>
        <router-link to="/seize" class="anchor">seize</router-link>
        <p>to quick start. ] -</p>
      </div>
    </div>

    <!-- 特性卡片 -->
    <div class="features">
      <ul class="featureList">
        <li class="feature">
          <h3>Fast</h3>
          <p>Speed up by almost 100%</p>
        </li>
        <li class="feature">
          <h3>Safe</h3>
          <p>All parameters saved locally</p>
        </li>
        <li class="feature">
          <h3>Simple</h3>
          <p>With no extra complex steps</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped lang="scss">
.home {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.hero {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 7vh 0 2.14vh; /* 原 80px 0 20px   */
  text-align: center;
}

.title {
  font-size: 5.88vw; /* 原 100px   */
  margin: 0;
  text-shadow: 4px 4px #ccc;
}

.subtitle {
  width: 100%;
  font-size: 2.94vw; /* 原 50px   */
  margin: 2.14vh 0; /* 原 20px 0   */
  padding: 0 var(--lr-align-padding);
  font-style: italic;
}

.cta {
  justify-content: center;
  align-items: center;
  display: flex;

  p {
    margin: inherit;
  }

  .anchor {
    height: 20px;
    border-radius: var(--radius-rounded);
    transition: var(--speed-quickTransition) var(--ease-out-quad);
    background: #e5e5e6;
    transition-property: color, background;
    margin: 0 12px;
    padding: 0 6px;
    font-style: italic;
    position: relative;
    &:hover {
      color: #000;
      background: #fff;
    }
  }
}

.features {
  display: flex;
  justify-content: center;
  margin: 2.14vh 0; /* 原 20px 0   */
  padding: 0 var(--lr-align-padding);
}

.featureList {
  width: 100%;
  display: flex;
  justify-content: center;

  /*
  原内容：
  gap: var(--feature-box-width);
  响应式内容：
*/
  column-gap: var(--feature-box-width);
  row-gap: clamp(16px, 3vw, 250px);
  flex-wrap: wrap;
}

.feature {
  width: var(--feature-box-width);
  /*height: var(--feature-box-height); */
  padding: 3.42vh 1.41vw; /* 原 32px 24px   */
  background: #e368ff;
  border-radius: 8px;
  text-align: center;
  box-shadow: var(--box-shadow);
  transition: 0.5s;

  h3 {
    margin: 0 0 4.28vh; /* 原 0 0 40px   */
    font-size: 1.77vw; /* 原 30px   */
  }

  p {
    margin: 0;
    font-size: 1.06vw; /* 原 18px   */
    font-weight: bolder;
  }

  &:hover {
    transform: scale(1.05);
  }

  /*
  原内容：
  无
  响应式内容：
*/
  aspect-ratio: 250 / 230;
}

/*
  原内容：
  无
  响应式内容：
*/
@media (max-width: 768px) {
  .hero {
    padding: 4vh 0 2.14vh; /* 原 80px 0 20px   */
  }
  .title {
    font-size: 28px;
  }
  .subtitle {
    font-size: 18px;
  }
  .subtitle p {
    text-align: center;
  }
  .feature h3 {
    font-size: 20px;
    margin-bottom: 16px;
  }
  .feature p {
    font-size: 14px;
  }
  .feature {
    max-width: 100%;
    height: 180px;
    aspect-ratio: auto;
  }
}
</style>
