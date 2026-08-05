(function () {
    'use strict';

    var container = document.getElementById('appBreadcrumbs');
    if (!container) {
        return;
    }

    var list = container.querySelector('.breadcrumb');
    var segments = window.location.pathname.split('/').filter(Boolean);
    var sections = {
        'catalogo': { label: 'Catalogos', url: '/catalogo' },
        'detalle-catalogo': { label: 'Detalle de catalogo', url: '/detalle-catalogo' },
        'usuarios': { label: 'Usuarios', url: '/usuarios' },
        'proveedores': { label: 'Proveedores', url: '/proveedores' },
        'productos': { label: 'Productos', url: '/productos' },
        'paciente': { label: 'Pacientes', url: '/paciente' },
        'examenesvisuales': { label: 'Examenes visuales', url: '/examenes-visuales' },
        'examenes-visuales': { label: 'Examenes visuales', url: '/examenes-visuales' },
        'ordenespedido': { label: 'Ordenes de pedido', url: '/ordenespedido' },
        'ordenes-entrega': { label: 'Ordenes de entrega', url: '/ordenes-entrega' },
        'certificados': { label: 'Certificados', url: '/certificados' }
    };
    var actionLabels = {
        'nuevo': 'Nuevo',
        'editar': 'Editar',
        'historial': 'Historial',
        'ver': 'Ver certificado'
    };
    var crumbs = [{ label: 'Inicio', url: '/' }];
    var section = sections[segments[0]];

    if (!section) {
        container.hidden = true;
        return;
    }

    crumbs.push({ label: section.label, url: section.url });

    if (segments[2] === 'detalles') {
        var detailUrl = '/' + segments[0] + '/' + segments[1] + '/detalles';
        crumbs.push({ label: 'Detalles', url: detailUrl });

        if (segments[3] === 'nuevo') {
            crumbs.push({ label: 'Nuevo' });
        } else if (segments[3] === 'editar') {
            crumbs.push({ label: 'Editar' });
        }
    } else if (actionLabels[segments[1]]) {
        crumbs.push({ label: actionLabels[segments[1]] });
    }

    crumbs[crumbs.length - 1].url = null;
    crumbs.forEach(function (crumb, index) {
        var item = document.createElement('li');
        var isLast = index === crumbs.length - 1;
        item.className = 'breadcrumb-item' + (isLast ? ' active' : '');

        if (crumb.url && !isLast) {
            var link = document.createElement('a');
            link.href = crumb.url;
            link.textContent = crumb.label;
            item.appendChild(link);
        } else {
            item.textContent = crumb.label;
            if (isLast) {
                item.setAttribute('aria-current', 'page');
            }
        }

        list.appendChild(item);
    });
}());
