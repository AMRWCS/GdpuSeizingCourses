<script setup lang="ts">
import type { SeizeDTO } from '@/api/course'
import { ref, watch } from 'vue'
import type { AxiosResponse } from 'axios'
import type { Result } from '@/utils/request'
import { WarningFilled } from '@element-plus/icons-vue'

const props = defineProps<{
  type: string
  onSubmit: (dataDTO: SeizeDTO) => Promise<AxiosResponse<Result<{ jxbIds: string }>>>
}>()

const STORAGE_KEY = 'seizeForm'

const formData = ref(loadLocalForm())

watch(
  formData,
  (val) => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(val))
  },
  { deep: true },
)

function createDefaultForm() {
  return {
    kchId: '',
    qz: '100',

    njdmId: '',
    xkxnm: '',
    xkxqm: '',
    kklxdm: '',
    xkkzId: '',

    route: '',
    JSESSIONID: '',
  }
}

function loadLocalForm() {
  const localForm = localStorage.getItem(STORAGE_KEY)
  if (localForm) {
    try {
      return { ...createDefaultForm(), ...JSON.parse(localForm) }
    } catch {}
  }
  return createDefaultForm()
}

function handleReset() {
  formData.value = createDefaultForm()
}

async function handleSubmit() {
  //非空校验
  const emptyFields = Object.entries(formData.value)
    .filter(([, value]) => value === '' || value === null || value === undefined)
    .map(([key]) => key)

  if (emptyFields.length > 0) {
    ElMessageBox.alert(
      `These fields're not allowed to be empty: ${emptyFields.join(', ')}`,
      'Notice',
      {
        confirmButtonText: 'OK',
      },
    )
    return
  }

  //组装表单
  const jxbIds = localStorage.getItem('jxbIds')

  const dataDTO: SeizeDTO = {
    kchId: formData.value.kchId,
    jxbIds: jxbIds || undefined,
    qz: formData.value.qz,
    jxbIdsRequest: {
      njdmId: formData.value.njdmId,
      xkxnm: formData.value.xkxnm,
      xkxqm: formData.value.xkxqm,
      kklxdm: formData.value.kklxdm,
      xkkzId: formData.value.xkkzId,
    },
    cookie: {
      route: formData.value.route,
      JSESSIONID: formData.value.JSESSIONID,
    },
  }

  //发送请求
  try {
    console.log(`开始发送请求: ${props.type}`)
    ElMessage('Handling... (Respond within 45s at most)')
    const res = await props.onSubmit(dataDTO)
    const { data } = res.data
    const jxbIds = data?.jxbIds
    if (jxbIds) {
      localStorage.setItem('jxbIds', jxbIds)
      console.log(`${props.type} 成功，jxbIds 已保存`)
    } else {
      console.warn('成功但响应未包含正确jxbIds')
    }
    ElMessage.success('Operation Success!')
  } catch {
    console.error('响应已拦截')
  }
}
</script>

<template>
  <div>
    <!-- 标题区域 -->
    <div class="title">{{ type }}</div>
    <!-- 提示区域 -->
    <div class="tip">
      Get params from:
      <a href="https://jwsys.gdpu.edu.cn" target="_blank" style="color: blue"
        >GDPU Official Website
      </a>
    </div>
    <!-- 表单区域 -->
    <ul class="formList">
      <li class="input-group">
        <span class="formType">Course:</span>
        <el-input v-model="formData.kchId" placeholder="kch_id (*)" clearable />
      </li>
      <li class="input-group">
        <span class="formType">User:</span>
        <el-input v-model="formData.njdmId" placeholder="njdm_id (*)" clearable />
        <el-input v-model="formData.xkxnm" placeholder="xkxnm (*)" clearable />
        <el-input v-model="formData.xkxqm" placeholder="xkxqm (*)" clearable />
        <el-input v-model="formData.kklxdm" placeholder="kklxdm (*)" clearable />
        <el-input v-model="formData.xkkzId" placeholder="xkkz_id (*)" clearable />
      </li>
      <li class="input-group">
        <span class="formType">Account:</span>
        <el-input v-model="formData.route" placeholder="route (*)" clearable />
        <el-input v-model="formData.JSESSIONID" placeholder="JSESSIONID (*)" clearable />
      </li>
    </ul>
    <!-- 按钮区域 -->
    <div class="btns">
      <el-popconfirm
        placement="top"
        :icon="WarningFilled"
        title="Are you sure to reset?"
        @confirm="handleReset"
      >
        <template #reference>
          <el-button type="primary">Reset</el-button>
        </template>
      </el-popconfirm>
      <el-button type="primary" @click="handleSubmit">Submit</el-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.title {
  text-align: center;
  font-weight: bold;
  font-size: 2.94vw; /* 原 50px   */
  border-bottom: 3px solid #000;
}

.tip {
  text-align: center;
  font-style: italic;
  color: #5c5c5c;
  margin: 1.07vh 0; /* 原 10px 0   */
}

.formList {
  display: flex;
  flex-direction: column;
  gap: 5.35vh; /* 原 50px   */
}

.input-group {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1em 2em;
}

.formType {
  font-weight: bold;
  font-size: 1.88vw; /* 原 32px   */
  grid-column: 1 / -1;
}

.el-input {
  width: 100%;
  margin-left: 0;
}

.btns {
  display: flex;
  justify-content: space-between;
  margin: 4.28vh 11.18vw 4.28vh 11.77vw; /* 原 40px 190px 40px 200px   */
}

.el-button {
  height: 4.28vh; /* 原 40px   */
}

/*
  原内容：
  无
  响应式内容：
*/
@media (max-width: 768px) {
  .title {
    font-size: 28px;
  }
  .formType {
    font-size: 16px;
  }
  .input-group {
    grid-template-columns: 1fr;
    gap: 12px;
  }
  .btns {
    margin: 24px 16px;
    gap: 16px;
  }
  .el-button {
    height: 30px;
  }
}
</style>
