import 'package:flutter/material.dart';

class DetalhesNFSE extends StatelessWidget {
  final String numeroNotaFiscal;
  final String data;
  final double valorTotal;
  final bool pessoaJuridica;
  final String razaoSocial;
  final String inscricaoMunicipal;

  const DetalhesNFSE({
    super.key,
    required this.numeroNotaFiscal,
    required this.data,
    required this.valorTotal,
    required this.razaoSocial,
    required this.inscricaoMunicipal,
    this.pessoaJuridica = false,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Detalhes da Nota Fiscal'),
      ),
      body: Container(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Número: $numeroNotaFiscal',
              style: const TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 18.0,
              ),
            ),
            const SizedBox(height: 8.0),
            Text(
              'Data: $data',
              style: const TextStyle(fontSize: 16.0),
            ),
            const SizedBox(height: 8.0),
            Text(
              'Valor Total: R\$ $valorTotal',
              style: const TextStyle(fontSize: 16.0),
            ),
            const SizedBox(height: 16.0),
            const Text(
              'Informações Fiscais:',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 18.0,
              ),
            ),
            const SizedBox(height: 8.0),
            Row(
              children: [
                Text(
                  'Pessoa ${pessoaJuridica ? 'Jurídica' : 'Física'}:',
                  style: const TextStyle(fontSize: 16.0),
                ),
                const SizedBox(width: 8.0),
                Text(
                  pessoaJuridica ? 'CNPJ' : 'CPF',
                  style: const TextStyle(fontSize: 16.0),
                ),
              ],
            ),
            const SizedBox(height: 8.0),
            if (pessoaJuridica)
              TextFormField(
                initialValue: razaoSocial,
                decoration: const InputDecoration(
                  labelText: 'Razão Social',
                ),
                readOnly: true,
              ),
            const SizedBox(height: 8.0),
            if (pessoaJuridica)
              TextFormField(
                initialValue: inscricaoMunicipal,
                decoration: const InputDecoration(
                  labelText: 'Inscrição Municipal',
                ),
                readOnly: true,
              ),
            const SizedBox(height: 16.0),
            const Text(
              'Produtos:',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 18.0,
              ),
            ),
            const SizedBox(height: 8.0),
            Expanded(
              child: ListView.builder(
                itemCount: 5,
                itemBuilder: (BuildContext context, int index) {
                  return ListTile(
                    title: Text('Produto ${index + 1}'),
                    subtitle: const Text('Quantidade: 1'),
                    trailing: const Text('R\$ 10.00'),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
