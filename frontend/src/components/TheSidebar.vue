<template>
  <b-sidebar id="sidebar-right" title="Requests List" width="20%" right shadow no-close-on-route-change>
    <p class="px-3 py-2">
      <b-list-group role="tab">
        <transition-group v-if="requests.length" name="list-complete" tag="div">
          <b-list-group-item v-for="(request, index) in requests" :key="request.date"
                             href="#"
                             class="flex-column align-items-start list-complete-item"
                             v-b-toggle="'accordion-request-' + request.date">
            <div class="d-flex w-100 justify-content-between">
              <div class="h6">
                <span class="font-weight-light">{{ request.type }} </span>
                <span class="text-muted">{{ request.path }}</span>
              </div>
              <div class="h6">{{ request.instanceIds.length }} <span class="font-weight-lighter">ws</span></div>
            </div>
            <b-collapse :id="'accordion-request-' + request.date" accordion="accordion-request" role="tabpanel">

              <div v-if="request.params && Object.keys(request.params).length" class="mb-1">
                <small class="text-muted">params</small>
                <div>
                  <span v-for="(param, key) in request.params" :key="key" class="mr-1">
                    {{ key }}={{ param }}
                  </span>
                </div>
              </div>

              <div v-if="request.data && Object.keys(request.data).length" class="mb-1">
                <small class="text-muted">request body/json:</small>
                <div>
                  <span v-for="(data, key) in request.data" :key="key" class="mr-1">
                    {{ key }}={{ data }}
                  </span>
                </div>
              </div>

              <div v-if="request.instanceIds.length" class="mb-2">
                <div class="text-muted"><small>Microservices involved:</small></div>
                <div>
                  <div v-for="instanceId in request.instanceIds" :key="instanceId">
                    <b-badge :variant="instanceId2variant(instanceId)"
                             class="pb-1">
                      {{ instanceId }}
                    </b-badge>
                  </div>
                </div>
              </div>

              <b-button @click.stop v-b-toggle="'collapse-response-' + request.date" size="sm" class="mb-2" block>
                Response
              </b-button>

              <b-collapse :id="'collapse-response-' + request.date" class="mb-2">
                <small class="mb-1">
                  <pre class="response-json overflow-auto" style="">{{ stringify(request.response) }}</pre>
                </small>
              </b-collapse>

              <small class="float-right">{{ request.date | date('hh:mm:ss.SSS') }}</small>
            </b-collapse>
          </b-list-group-item>
        </transition-group>
      </b-list-group>
    </p>
    <template v-slot:footer="{ hide }">
      <div class="d-flex bg-secondary text-light align-items-center px-3 py-2">
        <b-button class="mr-auto" size="sm" @click="hide"><b-icon icon="x" aria-label="Close"></b-icon> Close</b-button>
        <b-button size="sm" @click="clear"><b-icon icon="trash" aria-label="Clear"></b-icon> Clear</b-button>
      </div>
    </template>
  </b-sidebar>
</template>

<script>

const ws2variant = {
  'user-ws': 'primary',
  'film-ws': 'info',
  'person-ws': 'success',
  'watched-film-ws': 'danger',
  'OMDB': 'warning',
};

export default {
  name: 'TheSidebar',
  props: {
    requests: Array,
  },
  methods: {
    stringify(json) {
      return JSON.stringify(json, null, 2);
    },
    instanceId2variant(instanceId) {

      if (!instanceId) return null;

      const colonIndex = instanceId.indexOf(':');
      const wsInstanceId = colonIndex >= 0 ? instanceId.slice(0, colonIndex) : instanceId;

      return ws2variant[wsInstanceId] || 'primary';
    },
    clear() {
      this.requests.splice(0, this.requests.length);
    },
  },
};
</script>

<style lang="scss" scoped>
pre.response-json {
  max-height: 300px;
}

.list-complete-item {
  display: inline-block;
  transition: all 1s;
}

.list-complete-enter, .list-complete-leave-to {
  transform: translateY(30px);
  opacity: 0;
}

.list-complete-move {
  transition: transform .6s;
}

.list-complete-leave-active {
  position: absolute;
}
</style>
